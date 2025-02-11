package dev.bitbakery.boilerplate.post.route

import dev.bitbakery.boilerplate.post.service.PostService
import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
class PostRouting(
    private val service: PostService,
) {
    fun configure(application: Application) {
        application.routing {
            get<PostResource> {
                call.respond(service.getPosts())
            }
            get<PostResource.Detail> { detail ->
                call.respond(service.getPost(detail.postId))
            }
        }
    }
}

@Resource("/posts/")
private class PostResource {
    @Resource("{postId}/")
    class Detail(
        val postId: Long,
        val parent: PostResource = PostResource(),
    )
}
