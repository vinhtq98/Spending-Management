package com.jrsoftware.tool.spending.management.utils

import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.app.AppConstants
import com.jrsoftware.tool.spending.management.model.CategoriesModel

object Utils {

    fun initListCategories(): MutableList<CategoriesModel>{
        val listCategoriesModel = mutableListOf<CategoriesModel>()
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_food,"Food",AppConstants.FOOD,true))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_houseware,"House ware",AppConstants.HOUSE_WARE))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_clothes,"Clothes",AppConstants.CLOTHES))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_medical,"Medical",AppConstants.MEDICAL))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_education,"Education",AppConstants.EDUCATION))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_electric_bill,"Electric bill",AppConstants.ELECTRIC_BILL))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_transport,"Transport",AppConstants.TRANSPORT))
        listCategoriesModel.add(CategoriesModel(R.drawable.ic_contact,"Contact fee",AppConstants.CONTACT_FEE))
        return listCategoriesModel
    }
}