package com.jrsoftware.tool.spending.management.ui.component.main.fragment

import androidx.fragment.app.activityViewModels
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.databinding.FragmentCalendarBinding
import com.jrsoftware.tool.spending.management.model.CalendarModel
import com.jrsoftware.tool.spending.management.ui.base.BaseFragment
import com.jrsoftware.tool.spending.management.ui.component.main.SpendingViewModel
import com.jrsoftware.tool.spending.management.ui.component.main.adapter.CalendarAdapter
import com.jrsoftware.tool.spending.management.utils.custonview.SpendingDayDecorator
import java.util.Calendar

class CalendarFragment : BaseFragment<FragmentCalendarBinding>() {
    private var calendarAdapter: CalendarAdapter? = null
    private val viewModel: SpendingViewModel by activityViewModels()

    override fun getLayoutFragment(): Int = R.layout.fragment_calendar

    override fun initViews() {
        super.initViews()
        activity?.let { act ->
            calendarAdapter = CalendarAdapter()
            mBinding.rcvCalendar.adapter = calendarAdapter
            viewModel.allSpendings.observe(viewLifecycleOwner) { spendings ->
                val spendingPerDay = spendings.groupBy {
                    normalizeDate(it.day)
                }
                val decorators = spendingPerDay.map { (millis) ->
                    SpendingDayDecorator(act, millis)
                }
                mBinding.calendarView.removeDecorators()
                decorators.forEach { mBinding.calendarView.addDecorator(it) }
            }
        }

        mBinding.calendarView.setOnDateChangedListener { _, date, _ ->
            val calendar = Calendar.getInstance()
            calendar.set(date.year, date.month - 1, date.day)
            val dayStart = normalizeDate(calendar.timeInMillis)
            val filtered = viewModel.allSpendings.value?.filter {
                normalizeDate(it.day) == dayStart
            } ?: emptyList()
            val list = mutableListOf<CalendarModel>()
            list.add(CalendarModel(dayStart, filtered.toMutableList()))
            calendarAdapter?.submitData(list)
        }


    }

    private fun normalizeDate(millis: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}