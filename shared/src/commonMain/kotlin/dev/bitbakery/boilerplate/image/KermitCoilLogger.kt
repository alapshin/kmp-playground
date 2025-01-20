package dev.bitbakery.boilerplate.image

import co.touchlab.kermit.Logger.Companion.setLogWriters
import co.touchlab.kermit.Severity
import co.touchlab.kermit.chunked
import co.touchlab.kermit.platformLogWriter
import coil3.util.Logger

class KermitCoilLogger(
    override var minLevel: Logger.Level,
) : Logger {
    companion object {
        const val TAG = "Coil"
    }

    private val logger =
        co.touchlab.kermit.Logger.withTag(TAG).apply {
            setLogWriters(platformLogWriter().chunked())
        }

    private fun Logger.Level.toSeverity(): Severity =
        when (this) {
            Logger.Level.Verbose -> Severity.Verbose
            Logger.Level.Debug -> Severity.Debug
            Logger.Level.Info -> Severity.Info
            Logger.Level.Warn -> Severity.Warn
            Logger.Level.Error -> Severity.Error
        }

    override fun log(
        tag: String,
        level: Logger.Level,
        message: String?,
        throwable: Throwable?,
    ) {
        logger.log(
            tag = TAG,
            severity = level.toSeverity(),
            message = message.orEmpty(),
            throwable = throwable,
        )
    }
}
