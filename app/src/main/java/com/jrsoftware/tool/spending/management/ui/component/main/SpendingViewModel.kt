package com.jrsoftware.tool.spending.management.ui.component.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jrsoftware.tool.spending.management.database.AppDatabase
import com.jrsoftware.tool.spending.management.database.SpendingRepository
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity
import kotlinx.coroutines.launch

class SpendingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SpendingRepository
    val allSpendings: LiveData<List<SpendingEntity>>

    init {
        val dao = AppDatabase.getDatabase(application).spendingDao()
        repository = SpendingRepository(dao)
        allSpendings = repository.allSpendings
    }

    fun insert(spending: SpendingEntity) = viewModelScope.launch {
        repository.insert(spending)
    }
}
