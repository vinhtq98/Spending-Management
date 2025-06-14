package com.jrsoftware.tool.spending.management.ui.component.main.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.app.AppConstants
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity
import com.jrsoftware.tool.spending.management.databinding.ItemReportBinding
import com.jrsoftware.tool.spending.management.databinding.ItemSpendingBinding
import com.jrsoftware.tool.spending.management.model.TotalModel
import com.jrsoftware.tool.spending.management.ui.base.BaseRecyclerViewAdapter
import com.jrsoftware.tool.spending.management.ui.base.ext.goneView
import com.jrsoftware.tool.spending.management.ui.base.ext.visibleView
import kotlinx.coroutines.flow.combine

class ReportAdapter() : BaseRecyclerViewAdapter<TotalModel>() {
    override fun getItemLayout(): Int = R.layout.item_report

    override fun setData(binding: ViewDataBinding, item: TotalModel, layoutPosition: Int) {
        if(binding is ItemReportBinding){
            when(item.type){
                AppConstants.FOOD->{
                    binding.imvCategories.setImageResource(R.drawable.ic_food)
                    binding.tvName.text = "Food"
                }

                AppConstants.HOUSE_WARE->{
                    binding.imvCategories.setImageResource(R.drawable.ic_houseware)
                    binding.tvName.text = "House ware"
                }

                AppConstants.CLOTHES->{
                    binding.imvCategories.setImageResource(R.drawable.ic_clothes)
                    binding.tvName.text = "Clothes"
                }

                AppConstants.MEDICAL->{
                    binding.imvCategories.setImageResource(R.drawable.ic_medical)
                    binding.tvName.text = "Medical"
                }

                AppConstants.EDUCATION->{
                    binding.imvCategories.setImageResource(R.drawable.ic_education)
                    binding.tvName.text = "Education"
                }
                AppConstants.ELECTRIC_BILL->{
                    binding.imvCategories.setImageResource(R.drawable.ic_electric_bill)
                    binding.tvName.text = "Electric bill"
                }

                AppConstants.TRANSPORT->{
                    binding.imvCategories.setImageResource(R.drawable.ic_transport)
                    binding.tvName.text = "Transport"
                }

                AppConstants.CONTACT_FEE->{
                    binding.imvCategories.setImageResource(R.drawable.ic_contact)
                    binding.tvName.text = "Contact fee"
                }
            }
            binding.tvExpense.text = "${item.amount} $"
            binding.tvPercent.text = "${item.percent} %"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<TotalModel>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}