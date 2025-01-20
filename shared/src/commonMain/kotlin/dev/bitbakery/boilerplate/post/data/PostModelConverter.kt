package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.store5.Converter
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
class PostModelConverter : Converter<PostNetworkModel, PostDatabaseModel, PostDomainModel> {
    override fun fromOutputToLocal(output: PostDomainModel): PostDatabaseModel =
        PostDatabaseModel(
            id = output.id,
        )

    override fun fromNetworkToLocal(network: PostNetworkModel): PostDatabaseModel =
        PostDatabaseModel(
            id = network.id,
        )
}
