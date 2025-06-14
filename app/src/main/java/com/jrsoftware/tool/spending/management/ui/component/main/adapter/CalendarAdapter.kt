package com.jrsoftware.tool.spending.management.ui.component.main.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.databinding.ItemCalendarBinding
import com.jrsoftware.tool.spending.management.model.CalendarModel
import com.jrsoftware.tool.spending.management.ui.base.BaseRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarAdapter : BaseRecyclerViewAdapter<CalendarModel>() {

    override fun getItemLayout(): Int = R.layout.item_calendar

    override fun setData(binding: ViewDataBinding, item: CalendarModel, layoutPosition: Int) {
        if (binding is ItemCalendarBinding) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = item.day
            val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
            val formattedDate = formatter.format(calendar.time)
            binding.tvDay.text = formattedDate
            var total = 0L
            item.list.forEach {
                total += it.expense
            }
            binding.tvTotalMoney.text = "$total $"
            val spendingAdapter = SpendingAdapter()
            binding.rcvCalendar.adapter= spendingAdapter
            spendingAdapter.submitData(item.list)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitData(newData: List<CalendarModel>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}