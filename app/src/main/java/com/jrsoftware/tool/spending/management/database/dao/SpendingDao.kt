package com.jrsoftware.tool.spending.management.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpending(spending: SpendingEntity)

    @Update
    suspend fun updateSpending(spending: SpendingEntity)

    @Delete
    suspend fun deleteSpending(spending: SpendingEntity)

    @Query("SELECT * FROM spending_table ORDER BY day DESC")
    fun getAllSpendings(): LiveData<List<SpendingEntity>>

    @Query("SELECT * FROM spending_table WHERE day BETWEEN :start AND :end ORDER BY day DESC")
    suspend fun getSpendingsBetween(start: Long, end: Long): List<SpendingEntity>
}
