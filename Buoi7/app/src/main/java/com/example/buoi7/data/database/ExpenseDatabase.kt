package com.example.buoi7.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buoi7.data.dao.ExpenseDao
import com.example.buoi7.data.model.ExpenseModel

@Database (entities = [ExpenseModel::class], version = 2, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao() : ExpenseDao //Room sẽ tự động  hàm override

    companion object {
        // Ghi giá trị thay đổi vào bộ nhớ chính (thay vì cache)
        // để tất cả các Thread đều thấy giá trị mới.
        @Volatile
        private var INSTANCE : com.example.buoi7.data.database.ExpenseDatabase? = null

        fun getDatabase(context: Context) : com.example.buoi7.data.database.ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.buoi7.data.database.ExpenseDatabase::class.java,
                    "buoi_7_room_expense_database.db"
                ).build()
                instance
            }
        }
    }
}