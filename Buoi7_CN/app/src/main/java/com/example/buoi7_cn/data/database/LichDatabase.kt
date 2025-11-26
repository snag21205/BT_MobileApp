package com.example.buoi7_cn.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buoi7_cn.data.dao.LichDao
import com.example.buoi7_cn.data.model.Lich

@Database(entities = [Lich::class], version = 1, exportSchema = false)
abstract class LichDatabase : RoomDatabase() {
    abstract fun lichDao(): LichDao

    companion object {
        @Volatile
        private var INSTANCE: LichDatabase? = null

        fun getDatabase(context: Context): LichDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LichDatabase::class.java,
                    "lich_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

