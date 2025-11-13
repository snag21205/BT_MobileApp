package com.example.buoi7.data.dao

//import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.buoi7.data.model.ExpenseModel

@Dao
interface ExpenseDao {
    //suspend cho thao tác db chỉ thực hiện 1 lần
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseModel)

    // Dùng fun trả về LiveData hoặc Flow cho các truy vấn
    // mà bạn muốn UI tự động cập nhật khi dữ liệu thay đổi.
    @Query ("SELECT * FROM ExpenseModel ORDER BY id DESC")
    fun getAllExpenses(): LiveData<List<ExpenseModel>>

    @Query ("SELECT SUM(amount) FROM ExpenseModel")
    fun getTotalExpense(): LiveData<Double>

    @Query("SELECT * FROM ExpenseModel WHERE id = :expenseId")
    suspend fun getExpenseById(expenseId: Int): ExpenseModel

    @Delete
    suspend fun deleteExpense(expense: ExpenseModel)

    @Update
    suspend fun updateExpense(expense: ExpenseModel)
}
