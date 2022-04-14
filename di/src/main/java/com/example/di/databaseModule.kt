package com.example.di

import androidx.room.Room
import com.example.data_local.database.AppDatabase
import com.example.data_local.utils.SQLCipherHelper
import com.example.di.BuildConfig.SQLCIPHER_PASSCODE
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "user-database"
        ).apply {
            if (BuildConfig.DEBUG) return@apply
            openHelperFactory(
                SupportFactory(
                    SQLiteDatabase.getBytes(
                        (SQLCipherHelper(androidApplication()).getCharKey(SQLCIPHER_PASSCODE.toCharArray())).toCharArray()
                    )
                )
            )
        }.fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().movieDao() }
}