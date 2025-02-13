package dev.bitbakery.boilerplate.post.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
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

typealias PostDetailStore = Store<Long, PostDomainModel>

@Inject
@SingleIn(AppScope::class)
@Suppress("UnusedPrivateProperty")
class PostDetailStoreFactory(
    private val api: PostApi,
    private val database: Database,
    private val mapper: PostMapper,
    private val converter: PostModelConverter,
) {
    fun create(): PostDetailStore =
        StoreBuilder
            .from(
                fetcher = createFetcher(),
                sourceOfTruth = createSourceOfTruth(),
                converter = createConverter(),
            ).build()

    private fun createFetcher(): Fetcher<Long, PostNetworkModel> =
        Fetcher.ofResult { key ->
            api.getPost(PostApi.postDetailUrl(key)).toFetcherResult()
        }

    private fun createSourceOfTruth(): SourceOfTruth<Long, PostDatabaseModel, PostDomainModel> =
        SourceOfTruth.of(
            reader = { key ->
                database.postQueries
                    .selectPostById(key, mapper::map)
                    .asFlow()
                    .mapToOne(Dispatchers.IO)
            },
            writer = { key, post -> },
        )

    private fun createConverter(): Converter<PostNetworkModel, PostDatabaseModel, PostDomainModel> =
        Converter
            .Builder<PostNetworkModel, PostDatabaseModel, PostDomainModel>()
            .fromOutputToLocal { output -> converter.fromOutputToLocal(output) }
            .fromNetworkToLocal { network -> converter.fromNetworkToLocal(network) }
            .build()

    @Suppress("UnusedPrivateMember")
    private fun createUpdater(): Updater<Long, PostNetworkModel, Boolean> {
        TODO()
    }

    @Suppress("UnusedPrivateMember")
    private fun createBookkeeper(): Bookkeeper<Int> {
        TODO()
    }
}
