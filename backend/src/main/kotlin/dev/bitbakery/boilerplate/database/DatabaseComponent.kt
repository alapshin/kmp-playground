package dev.bitbakery.boilerplate.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.bitbakery.boileplate.database.Database
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import java.util.Properties

@ContributesTo(AppScope::class)
interface DatabaseComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabase(driver: SqlDriver): Database = Database(driver)

    @Provides
    @SingleIn(AppScope::class)
    fun provideSqlDriver(urlProvider: DatabaseUrlProvider): SqlDriver {
        val path = urlProvider.url ?: "memory"
        val jdbcUrl = "jdbc:sqlite:$path"
        val properties = Properties().apply { put("foreign_keys", "true") }
        return JdbcSqliteDriver(url = jdbcUrl, properties = properties).also {
            Database.Schema.create(it)
        }
    }
}
