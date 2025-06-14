package com.jrsoftware.tool.spending.management.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesModel(val resId: Int,val name: String, val type: String,var isSelected : Boolean = false) : Parcelable