package com.jrsoftware.tool.spending.management.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jrsoftware.tool.spending.management.database.dao.SpendingDao
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity

@Database(entities = [SpendingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "spending_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
