package com.example.buoi7_cn.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.buoi7_cn.data.database.LichDatabase
import com.example.buoi7_cn.data.model.Lich
import com.example.buoi7_cn.repository.LichRepository
import kotlinx.coroutines.launch

class LichViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LichRepository
    val allLich: LiveData<List<Lich>>

    init {
        val lichDao = LichDatabase.getDatabase(application).lichDao()
        repository = LichRepository(lichDao)
        allLich = repository.allLich
    }

    fun insert(lich: Lich) = viewModelScope.launch {
        repository.insert(lich)
    }

    fun update(lich: Lich) = viewModelScope.launch {
        repository.update(lich)
    }

    fun delete(lich: Lich) = viewModelScope.launch {
        repository.delete(lich)
    }

    fun getLichByDateRange(startDate: Long, endDate: Long): LiveData<List<Lich>> {
        return repository.getLichByDateRange(startDate, endDate)
    }

    fun getLichFromDate(startDate: Long): LiveData<List<Lich>> {
        return repository.getLichFromDate(startDate)
    }
}

