package dev.bitbakery.boilerplate.post.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.bitbakery.boilerplate.base.toFetcherResult
import dev.bitbakery.boilerplate.database.Database
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import kotlinx.coroutines.Dispatchers
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.Updater
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

typealias PostListStore = Store<Unit, List<PostDomainModel>>

@Inject
@SingleIn(AppScope::class)
class PostListStoreFactory(
    private val api: PostApi,
    private val database: Database,
    private val mapper: PostMapper,
    private val converter: PostModelConverter,
) {
    fun create(): PostListStore =
        StoreBuilder
            .from(
                fetcher = createFetcher(),
                sourceOfTruth = createSourceOfTruth(),
                converter = createConverter(),
            ).build()

    private fun createFetcher(): Fetcher<Unit, List<PostNetworkModel>> =
        Fetcher.ofResult {
            api.getPosts().toFetcherResult()
        }

    private fun createSourceOfTruth(): SourceOfTruth<Unit, List<PostDatabaseModel>, List<PostDomainModel>> =
        SourceOfTruth.of(
            reader = {
                database.postQueries
                    .selectAllPosts(mapper::map)
                    .asFlow()
                    .mapToList(Dispatchers.IO)
            },
            writer = { _, posts ->
                database.postQueries.transaction {
                    posts.forEach { post ->
                        database.userQueries.insert(
                            id = post.user.id,
                            uuid = post.user.uuid.toString(),
                            username = post.user.username,
                        )
                        database.postQueries.insert(
                            id = post.id,
                            uuid = post.uuid.toString(),
                            user_id = post.user.id,
                            title = post.title,
                            content = post.content,
                            created_at = post.createdAt.toString(),
                        )
                    }
                }
            },
        )

    private fun createConverter(): Converter<List<PostNetworkModel>, List<PostDatabaseModel>, List<PostDomainModel>> =
        Converter
            .Builder<List<PostNetworkModel>, List<PostDatabaseModel>, List<PostDomainModel>>()
            .fromOutputToLocal { output -> output.map { converter.fromOutputToLocal(it) } }
            .fromNetworkToLocal { network -> network.map { converter.fromNetworkToLocal(it) } }
            .build()

    @Suppress("UnusedPrivateMember")
    private fun createUpdater(): Updater<Unit, PostNetworkModel, Boolean> {
        TODO()
    }

    @Suppress("UnusedPrivateMember")
    private fun createBookkeeper(): Bookkeeper<Int> {
        TODO()
    }
}
