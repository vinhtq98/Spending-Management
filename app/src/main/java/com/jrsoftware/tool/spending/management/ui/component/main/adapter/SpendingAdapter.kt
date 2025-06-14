package com.jrsoftware.tool.spending.management.ui.component.main.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.app.AppConstants
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity
import com.jrsoftware.tool.spending.management.databinding.ItemSpendingBinding
import com.jrsoftware.tool.spending.management.ui.base.BaseRecyclerViewAdapter
import com.jrsoftware.tool.spending.management.ui.base.ext.goneView
import com.jrsoftware.tool.spending.management.ui.base.ext.visibleView
import kotlinx.coroutines.flow.combine

class SpendingAdapter : BaseRecyclerViewAdapter<SpendingEntity>() {
    override fun getItemLayout(): Int = R.layout.item_spending

    override fun setData(binding: ViewDataBinding, item: SpendingEntity, layoutPosition: Int) {
        if(binding is ItemSpendingBinding){
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
            if(item.note.isNotEmpty()){
                binding.tvNote.visibleView()
                binding.tvNote.text = item.note
            }else{
                binding.tvNote.goneView()
            }
            binding.tvExpense.text = "${item.expense} $"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<SpendingEntity>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}