package dev.bitbakery.boilerplate.network

interface TokenStorage {
    suspend fun getToken(): String?

    suspend fun setToken(token: String?)
}
