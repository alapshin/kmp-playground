package dev.bitbakery.boilerplate.post.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.bitbakery.boilerplate.base.toFetcherResult
import dev.bitbakery.boilerplate.database.Database
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import dev.bitbakery.boilerplate.user.domain.UserDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Instant
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
import kotlin.uuid.Uuid

typealias PostListStore = Store<Unit, List<PostDomainModel>>

@Inject
@SingleIn(AppScope::class)
class PostListFactory(
    private val api: PostApi,
    private val database: Database,
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
                    .selectFull {
                            id,
                            uuid,
                            userId,
                            title,
                            content,
                            createdAt,
                            userUuid,
                            username,
                            likeCount,
                            commentCount,
                        ->
                        PostDomainModel(
                            id = id,
                            uuid = Uuid.parse(uuid),
                            title = title,
                            content = content,
                            createdAt = Instant.parse(createdAt),
                            user =
                                UserDomainModel(
                                    id = userId,
                                    uuid = Uuid.parse(userUuid),
                                    username = username,
                                ),
                            likeCount = likeCount,
                            commentCount = commentCount,
                        )
                    }.asFlow()
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
