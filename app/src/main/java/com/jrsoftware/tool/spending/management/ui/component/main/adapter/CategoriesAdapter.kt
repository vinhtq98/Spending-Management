package com.jrsoftware.tool.spending.management.ui.component.main.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.databinding.ItemCategoriesBinding
import com.jrsoftware.tool.spending.management.model.CategoriesModel
import com.jrsoftware.tool.spending.management.ui.base.BaseRecyclerViewAdapter
import com.jrsoftware.tool.spending.management.ui.base.ext.click

class CategoriesAdapter(private val onItemCategoriesClick : (CategoriesModel)->Unit) : BaseRecyclerViewAdapter<CategoriesModel>() {
    private var selectedPosition: Int = -1
    override fun getItemLayout(): Int = R.layout.item_categories

    override fun setData(binding: ViewDataBinding, item: CategoriesModel, layoutPosition: Int) {
        if(binding is ItemCategoriesBinding){
            binding.imvCategories.setImageResource(item.resId)
            binding.tvCategories.text = item.name
            if(item.isSelected){
                binding.root.setBackgroundResource(R.drawable.bg_item_categories_active)
            }else{
                binding.root.setBackgroundResource(R.drawable.bg_item_categories_inactive)
            }
        }
    }

    private fun handleItemClick(position: Int) {
        // Bỏ chọn item cũ nếu có
        if (selectedPosition != -1 && selectedPosition < list.size) {
            list[selectedPosition].isSelected = false
            notifyItemChanged(selectedPosition)
        }

        // Chọn item mới
        list[position].isSelected = true
        notifyItemChanged(position)

        selectedPosition = position

        // Gọi callback nếu cần (tùy theo bạn xử lý trong BaseRecyclerViewAdapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClickViews(binding: ViewDataBinding, obj: CategoriesModel, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)
        if(binding is ItemCategoriesBinding){
            binding.root.click {
                handleItemClick(layoutPosition)
                onItemCategoriesClick.invoke(obj)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<CategoriesModel>) {
        list.clear()
        list.addAll(newData)
        selectedPosition = list.indexOfFirst { it.isSelected }  // cập nhật lại vị trí được chọn
        notifyDataSetChanged()
    }
}