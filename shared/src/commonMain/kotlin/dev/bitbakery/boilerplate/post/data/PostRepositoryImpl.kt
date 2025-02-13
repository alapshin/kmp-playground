package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.base.DataError
import dev.bitbakery.boilerplate.base.DataState
import dev.bitbakery.boilerplate.base.toDataState
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.store.store5.StoreReadRequest
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class PostRepositoryImpl(
    private val listStore: PostListStore,
    private val detailStore: PostDetailStore,
) : PostRepository {
    override fun getPosts(): Flow<DataState<DataError, List<PostDomainModel>>> =
        listStore
            .stream(StoreReadRequest.fresh(key = Unit, fallBackToSourceOfTruth = false))
            .map { result -> result.toDataState() }

    override fun getPost(postId: Long): Flow<DataState<DataError, PostDomainModel>> =
        detailStore
            .stream(StoreReadRequest.fresh(key = postId, fallBackToSourceOfTruth = false))
            .map { result -> result.toDataState() }
}
