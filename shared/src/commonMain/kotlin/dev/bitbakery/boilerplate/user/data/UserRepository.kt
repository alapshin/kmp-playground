package dev.bitbakery.boilerplate.user.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun currentUser(): Flow<UserNetworkModel?>

    fun setCurrentUser(user: UserNetworkModel)
}
