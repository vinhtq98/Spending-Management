package com.jrsoftware.tool.spending.management.model

import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity

data class CalendarModel(val day: Long,val list: MutableList<SpendingEntity>)