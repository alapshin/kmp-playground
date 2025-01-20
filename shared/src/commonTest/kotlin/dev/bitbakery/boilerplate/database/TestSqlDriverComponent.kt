package dev.bitbakery.boilerplate.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import java.util.Properties

@ContributesTo(AppScope::class)
interface TestSqlDriverComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideSqlDriver(): SqlDriver {
        val url = JdbcSqliteDriver.IN_MEMORY
        val properties = Properties().apply { put("foreign_keys", "true") }
        return JdbcSqliteDriver(url = url, properties = properties).also {
            Database.Schema.create(it)
        }
    }
}
