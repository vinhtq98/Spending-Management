package com.jrsoftware.tool.spending.management.ui.component.main

import androidx.fragment.app.Fragment
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.databinding.ActivityMainBinding
import com.jrsoftware.tool.spending.management.ui.base.BaseActivity
import com.jrsoftware.tool.spending.management.ui.base.ext.click
import com.jrsoftware.tool.spending.management.ui.component.main.fragment.CalendarFragment
import com.jrsoftware.tool.spending.management.ui.component.main.fragment.InputFragment
import com.jrsoftware.tool.spending.management.ui.component.main.fragment.ReportFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val inputFragment by lazy { InputFragment() }
    private val calendarFragment by lazy { CalendarFragment() }
    private val reportFragment by lazy { ReportFragment() }

    override fun getLayoutActivity(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()
        addFragment(inputFragment)
        activeInputFragment()
    }

    override fun onClickViews() {
        super.onClickViews()
        mBinding.llInput.click {
            addFragment(inputFragment)
            activeInputFragment()
        }
        mBinding.llCalendar.click {
            addFragment(calendarFragment)
            activeCalendarFragment()
        }

        mBinding.llReport.click {
            addFragment(reportFragment)
            activeReportFragment()
        }
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        fragmentManager.fragments.forEach { transaction.hide(it) }
        val existingFragment = fragmentManager.findFragmentByTag(fragment::class.java.name)
        if (existingFragment != null) {
            transaction.show(existingFragment)
        } else {
            transaction.add(R.id.fragmentContainer, fragment, fragment::class.java.name)
        }
        transaction.commit()
    }

    private fun activeInputFragment() {
        mBinding.imvInput.setColorFilter(getColor(R.color.color_primary))
        mBinding.tvInput.setTextColor(getColor(R.color.color_primary))
        mBinding.imvCalendar.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvCalendar.setTextColor(getColor(R.color.color_neutral_1))
        mBinding.imvReport.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvReport.setTextColor(getColor(R.color.color_neutral_1))
    }

    private fun activeCalendarFragment() {
        mBinding.imvInput.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvInput.setTextColor(getColor(R.color.color_neutral_1))
        mBinding.imvCalendar.setColorFilter(getColor(R.color.color_primary))
        mBinding.tvCalendar.setTextColor(getColor(R.color.color_primary))
        mBinding.imvReport.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvReport.setTextColor(getColor(R.color.color_neutral_1))
    }

    private fun activeReportFragment() {
        mBinding.imvInput.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvInput.setTextColor(getColor(R.color.color_neutral_1))
        mBinding.imvCalendar.setColorFilter(getColor(R.color.color_neutral_1))
        mBinding.tvCalendar.setTextColor(getColor(R.color.color_neutral_1))
        mBinding.imvReport.setColorFilter(getColor(R.color.color_primary))
        mBinding.tvReport.setTextColor(getColor(R.color.color_primary))
    }
}