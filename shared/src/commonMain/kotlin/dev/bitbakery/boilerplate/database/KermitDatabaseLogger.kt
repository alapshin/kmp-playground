package dev.bitbakery.boilerplate.database

import co.touchlab.kermit.Logger.Companion.setLogWriters
import co.touchlab.kermit.chunked
import co.touchlab.kermit.platformLogWriter
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class KermitDatabaseLogger : DatabaseLogger {
    override fun log(message: String) {
        logger.d { message }
    }

    companion object {
        const val TAG = "SqlDelight"
    }

    private val logger =
        co.touchlab.kermit.Logger.withTag(TAG).apply {
            setLogWriters(platformLogWriter().chunked())
        }
}
