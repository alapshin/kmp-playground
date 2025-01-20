package dev.bitbakery.boilerplate.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.bitbakery.boilerplate.BuildKonfig
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface AndroidSqlDriverComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideSqlDriver(context: Context): SqlDriver =
        AndroidSqliteDriver(
            name = BuildKonfig.APP_NAME,
            schema = Database.Schema,
            context = context,
            callback =
                object : AndroidSqliteDriver.Callback(Database.Schema) {
                    override fun onOpen(database: SupportSQLiteDatabase) {
                        super.onOpen(database)
                        database.setForeignKeyConstraintsEnabled(true)
                    }
                },
        )
}
