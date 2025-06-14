package com.jrsoftware.tool.spending.management.utils.custonview

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.jrsoftware.tool.spending.management.R
import java.util.Calendar

class SpendingDayDecorator(
    private val context: Context,
    private val dateInMillis: Long
) : DayViewDecorator {

    private val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = dateInMillis
    }
    private val calendarDay = CalendarDay.from(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) +1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    private val highlightDrawable: Drawable? by lazy {
        ContextCompat.getDrawable(context, R.drawable.bg_spending_day)
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == calendarDay
    }

    override fun decorate(view: DayViewFacade) {
        highlightDrawable?.let {
            view.setBackgroundDrawable(it)
        }
    }
}
