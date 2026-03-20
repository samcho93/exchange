package com.project.exchange.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExchangeRateEntity::class], version = 1, exportSchema = false)
abstract class ExchangeDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}
