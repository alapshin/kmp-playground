package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import dev.bitbakery.boilerplate.user.data.UserDatabaseModel
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
            uuid = output.uuid,
            title = output.title,
            content = output.content,
            createdAt = output.createdAt,
            user =
                UserDatabaseModel(
                    id = output.user.id,
                    uuid = output.user.uuid,
                    username = output.user.username,
                ),
        )

    override fun fromNetworkToLocal(network: PostNetworkModel): PostDatabaseModel =
        PostDatabaseModel(
            id = network.id,
            uuid = network.uuid,
            title = network.title,
            content = network.content,
            createdAt = network.createdAt,
            user =
                UserDatabaseModel(
                    id = network.user.id,
                    uuid = network.user.uuid,
                    username = network.user.username,
                ),
        )
}
