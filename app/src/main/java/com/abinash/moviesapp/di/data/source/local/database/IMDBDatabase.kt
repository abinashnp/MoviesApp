package com.abinash.moviesapp.di.data.source.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abinash.moviesapp.di.data.source.local.convertors.ListStringConverter
import com.abinash.moviesapp.di.data.source.local.model.*
import java.util.concurrent.Executors

const val DB_NAME = "imdbnew_db"

@Database(
    entities = [
        MovieDB::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(ListStringConverter::class)
abstract class IMDbDatabase : RoomDatabase() {

    companion object {
        private var IMDbDatabaseInstance: IMDbDatabase? = null

        fun getInstance(context: Context): IMDbDatabase =
            buildDatabase(
                context
            )

        private fun buildDatabase(context: Context): IMDbDatabase {
            return Room.databaseBuilder(
                context,
                IMDbDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadScheduledExecutor()
                            .execute {
                                getInstance(
                                    context
                                )
                            }
                    }
                })
                .build()
        }
    }

    abstract fun movieDao(): MovieDao

}