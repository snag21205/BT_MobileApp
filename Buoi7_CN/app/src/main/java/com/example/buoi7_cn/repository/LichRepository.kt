package com.example.buoi7_cn.repository

import androidx.lifecycle.LiveData
import com.example.buoi7_cn.data.dao.LichDao
import com.example.buoi7_cn.data.model.Lich

class LichRepository(private val lichDao: LichDao) {

    val allLich: LiveData<List<Lich>> = lichDao.getAllLich()

    suspend fun insert(lich: Lich) {
        lichDao.insert(lich)
    }

    suspend fun update(lich: Lich) {
        lichDao.update(lich)
    }

    suspend fun delete(lich: Lich) {
        lichDao.delete(lich)
    }

    fun getLichByDateRange(startDate: Long, endDate: Long): LiveData<List<Lich>> {
        return lichDao.getLichByDateRange(startDate, endDate)
    }

    fun getLichFromDate(startDate: Long): LiveData<List<Lich>> {
        return lichDao.getLichFromDate(startDate)
    }
}

