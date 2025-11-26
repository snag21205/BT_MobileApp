package com.example.buoi7_cn.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.buoi7_cn.data.model.Lich

@Dao
interface LichDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lich: Lich)

    @Update
    suspend fun update(lich: Lich)

    @Delete
    suspend fun delete(lich: Lich)

    @Query("SELECT * FROM lich_table ORDER BY ngayGio ASC")
    fun getAllLich(): LiveData<List<Lich>>

    @Query("SELECT * FROM lich_table WHERE ngayGio BETWEEN :startDate AND :endDate ORDER BY ngayGio ASC")
    fun getLichByDateRange(startDate: Long, endDate: Long): LiveData<List<Lich>>

    @Query("SELECT * FROM lich_table WHERE ngayGio >= :startDate ORDER BY ngayGio ASC")
    fun getLichFromDate(startDate: Long): LiveData<List<Lich>>
}

