package com.jrsoftware.tool.spending.management.database

import androidx.lifecycle.LiveData
import com.jrsoftware.tool.spending.management.database.dao.SpendingDao
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity

class SpendingRepository(private val dao: SpendingDao) {
    val allSpendings: LiveData<List<SpendingEntity>> = dao.getAllSpendings()

    suspend fun insert(spending: SpendingEntity) {
        dao.insertSpending(spending)
    }
}
