package com.jrsoftware.tool.spending.management.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spending_table")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val expense: Long,
    val note: String,
    val day: Long,     // Lưu millis của ngày
    val type: String   // Ví dụ: "Food", "Transport", ...
)
