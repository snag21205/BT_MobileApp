package com.example.buoi7_cn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lich_table")
data class Lich(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hoVaTen: String,
    val ngayGio: Long, // Timestamp in milliseconds
    val noiDung: String,
    val linkAnh: String
)

