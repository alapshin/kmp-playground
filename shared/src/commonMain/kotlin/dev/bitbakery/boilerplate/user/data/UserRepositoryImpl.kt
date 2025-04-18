package dev.bitbakery.boilerplate.user.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class UserRepositoryImpl : UserRepository {
    private val userFlow: MutableStateFlow<UserNetworkModel?> = MutableStateFlow(null)

    override fun currentUser(): Flow<UserNetworkModel?> = userFlow.asStateFlow()

    override fun setCurrentUser(user: UserNetworkModel) {
        userFlow.value = user
    }
}
