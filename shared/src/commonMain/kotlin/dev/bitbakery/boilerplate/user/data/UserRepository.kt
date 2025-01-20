package dev.bitbakery.boilerplate.user.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun currentUser(): Flow<User?>

    fun setCurrentUser(user: User)
}
