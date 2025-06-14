package com.jrsoftware.tool.spending.management.ui.component.main.fragment

import android.graphics.Color
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.app.AppConstants
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity
import com.jrsoftware.tool.spending.management.databinding.FragmentReportBinding
import com.jrsoftware.tool.spending.management.model.TotalModel
import com.jrsoftware.tool.spending.management.ui.base.BaseFragment
import com.jrsoftware.tool.spending.management.ui.base.ext.goneView
import com.jrsoftware.tool.spending.management.ui.base.ext.visibleView
import com.jrsoftware.tool.spending.management.ui.component.main.SpendingViewModel
import com.jrsoftware.tool.spending.management.ui.component.main.adapter.ReportAdapter
import java.util.Locale
import kotlin.math.roundToInt

class ReportFragment : BaseFragment<FragmentReportBinding>() {
    private val viewModel: SpendingViewModel by activityViewModels()
    private var reportAdapter: ReportAdapter? = null
    override fun getLayoutFragment(): Int = R.layout.fragment_report

    private fun showPieChart(spendingList: List<SpendingEntity>) {

        val groupMap =
            spendingList.groupBy { it.type }.mapValues { entry -> entry.value.sumOf { it.expense } }

        val entries = ArrayList<PieEntry>()
        groupMap.forEach { (type, totalExpense) ->
            if (totalExpense > 0) {
                entries.add(PieEntry(totalExpense.toFloat(), getTypeLabel(type)))
            }
        }
        Log.d("VinhTQ", "showPieChart entries: ${entries.size}")
        val dataSet = PieDataSet(entries, "").apply {
            colors =
                (ColorTemplate.MATERIAL_COLORS + ColorTemplate.JOYFUL_COLORS + ColorTemplate.COLORFUL_COLORS + ColorTemplate.PASTEL_COLORS).toList()
            valueTextSize = 14f
            valueTextColor = Color.WHITE
            setDrawValues(true)
        }

        val data = PieData(dataSet)
        entries.forEachIndexed { index, pieEntry ->
            Log.d("PieEntryCheck", "[$index] Label: ${pieEntry.label}, Value: ${pieEntry.value}")
        }
        mBinding.pieChart.apply {
            this.data = data
            description.isEnabled = false
            isDrawHoleEnabled = true
            setEntryLabelColor(Color.BLACK)
            setUsePercentValues(true)
            invalidate() // Refresh
        }
        mBinding.pieChart.legend.apply {
            isEnabled = true
            verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            orientation = Legend.LegendOrientation.HORIZONTAL
            setDrawInside(false)
            xEntrySpace = 10f
            yEntrySpace = 5f
            textSize = 12f
        }
    }

    override fun initViews() {
        super.initViews()
        reportAdapter = ReportAdapter()
        mBinding.rcvType.adapter = reportAdapter
    }

    override fun observerData() {
        super.observerData()
        viewModel.allSpendings.observe(viewLifecycleOwner) { spending ->
            if (spending.isNotEmpty()) {
                mBinding.pieChart.visibleView()
                mBinding.rcvType.visibleView()
                mBinding.imvNothing.goneView()
                mBinding.tvNothing.goneView()
                showPieChart(spending)
                calculateExpensePercentageByType(spending)
            }else{
                mBinding.pieChart.goneView()
                mBinding.rcvType.goneView()
                mBinding.imvNothing.visibleView()
                mBinding.tvNothing.visibleView()
            }

        }
    }

    private fun getTypeLabel(type: String): String {
        return when (type) {
            AppConstants.FOOD -> "Food"
            AppConstants.HOUSE_WARE -> "House ware"
            AppConstants.CLOTHES -> "Clothes"
            AppConstants.COSMETIC -> "Cosmetic"
            AppConstants.MEDICAL -> "Medical"
            AppConstants.EDUCATION -> "Education"
            AppConstants.ELECTRIC_BILL -> "Electric"
            AppConstants.TRANSPORT -> "Transport"
            AppConstants.CONTACT_FEE -> "Contact"
            else -> "Other"
        }
    }

    private fun calculateExpensePercentageByType(spendingList: List<SpendingEntity>) {
        val totalExpense = spendingList.sumOf { it.expense }
        val groupMap =
            spendingList.groupBy { it.type }.mapValues { entry -> entry.value.sumOf { it.expense } }
        val listReportModel = mutableListOf<TotalModel>()
        groupMap.forEach { (type, amount) ->
            val percent = if (totalExpense > 0) {
                amount * 100.0 / totalExpense
            } else 0.0
            val roundedPercent = percent.roundToInt()
            listReportModel.add(TotalModel(amount, roundedPercent, type))
            Log.d("VinhTQ", "$type: $amount $, chiếm %.2f%%".format(percent))
        }
        reportAdapter?.submitData(listReportModel)
    }


}