package dev.bitbakery.boilerplate.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.logs.LogSqliteDriver
import dev.bitbakery.boilerplate.BuildKonfig
import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface AndroidSqlDriverComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideSqlDriver(
        context: Context,
        logger: DatabaseLogger,
        factory: SupportSQLiteOpenHelper.Factory,
        callback: AndroidSqliteDriver.Callback,
    ): SqlDriver =
        LogSqliteDriver(
            sqlDriver =
                AndroidSqliteDriver(
                    name = BuildKonfig.APP_NAME,
                    schema = Database.Schema,
                    context = context,
                    factory = factory,
                    callback = callback,
                ),
            logger = { message -> logger.log(message) },
        )

    @Provides
    @SingleIn(AppScope::class)
    fun provideOpenHelperFactory(): SupportSQLiteOpenHelper.Factory = RequerySQLiteOpenHelperFactory()

    @Provides
    @SingleIn(AppScope::class)
    fun provideSqliteDriverCallback() =
        object : AndroidSqliteDriver.Callback(Database.Schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                db.setForeignKeyConstraintsEnabled(true)
            }
        }
}
