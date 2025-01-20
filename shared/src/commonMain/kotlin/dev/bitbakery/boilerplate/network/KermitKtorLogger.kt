package dev.bitbakery.boilerplate.network

import co.touchlab.kermit.Logger.Companion.setLogWriters
import co.touchlab.kermit.chunked
import co.touchlab.kermit.platformLogWriter
import io.ktor.client.plugins.logging.Logger
import me.tatarka.inject.annotations.Inject

@Inject
class KermitKtorLogger : Logger {
    companion object {
        const val TAG = "Ktor"
    }

    private val logger =
        co.touchlab.kermit.Logger.withTag(TAG).apply {
            setLogWriters(platformLogWriter().chunked())
        }

    override fun log(message: String) {
        logger.i { message }
    }
}
