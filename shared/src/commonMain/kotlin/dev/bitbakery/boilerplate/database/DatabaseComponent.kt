package dev.bitbakery.boilerplate.database

import app.cash.sqldelight.db.SqlDriver
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface DatabaseComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideDatabase(driver: SqlDriver): Database = Database(driver)
}
