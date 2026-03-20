package com.project.exchange.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.project.exchange.data.local.ExchangeDatabase
import com.project.exchange.data.local.ExchangeRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExchangeDatabase =
        Room.databaseBuilder(
            context,
            ExchangeDatabase::class.java,
            "exchange_db"
        ).build()

    @Provides
    @Singleton
    fun provideExchangeRateDao(database: ExchangeDatabase): ExchangeRateDao =
        database.exchangeRateDao()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("exchange_prefs", Context.MODE_PRIVATE)
}
