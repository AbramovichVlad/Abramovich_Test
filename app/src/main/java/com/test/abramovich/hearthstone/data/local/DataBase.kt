package com.test.abramovich.hearthstone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.abramovich.hearthstone.domain.model.Card

@Database(
    entities = [Card::class],
    version = 1,
    exportSchema = false,
)
abstract class DataBase : RoomDatabase() {

    abstract fun favoriteCardDao(): FavoriteCardDao

    companion object {
        private const val DATABASE_NAME = "hearthstone_database"

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}