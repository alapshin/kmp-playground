package dev.bitbakery.boilerplate.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class TokenStorageImpl(
    private val dataStore: DataStore<Preferences>,
) : TokenStorage {
    override suspend fun getToken(): String? =
        dataStore.data
            .map { preferences -> preferences[AUTH_TOKEN] }
            .firstOrNull()

    override suspend fun setToken(token: String?) {
        dataStore.edit { preferences ->
            if (token == null) {
                preferences.remove(AUTH_TOKEN)
            } else {
                preferences[AUTH_TOKEN] = token
            }
        }
    }

    companion object {
        val AUTH_TOKEN = stringPreferencesKey("AUTH_TOKEN")
    }
}
