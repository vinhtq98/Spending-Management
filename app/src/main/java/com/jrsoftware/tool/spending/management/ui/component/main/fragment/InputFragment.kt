package com.jrsoftware.tool.spending.management.ui.component.main.fragment

import android.app.DatePickerDialog
import androidx.fragment.app.activityViewModels
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.app.AppConstants
import com.jrsoftware.tool.spending.management.database.entity.SpendingEntity
import com.jrsoftware.tool.spending.management.databinding.FragmentInputBinding
import com.jrsoftware.tool.spending.management.model.CategoriesModel
import com.jrsoftware.tool.spending.management.ui.base.BaseFragment
import com.jrsoftware.tool.spending.management.ui.base.ext.click
import com.jrsoftware.tool.spending.management.ui.base.ext.showToastById
import com.jrsoftware.tool.spending.management.ui.component.main.SpendingViewModel
import com.jrsoftware.tool.spending.management.ui.component.main.adapter.CategoriesAdapter
import com.jrsoftware.tool.spending.management.utils.Utils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class InputFragment : BaseFragment<FragmentInputBinding>() {
    private var _year = 0
    private var _month = 0
    private var _day = 0
    private var timeday = 0L
    private var _categoriesModel = CategoriesModel(R.drawable.ic_food,"Food", AppConstants.FOOD,true)
    private val viewModel : SpendingViewModel by activityViewModels()
    private var categoriesAdapter : CategoriesAdapter? = null

    override fun getLayoutFragment(): Int = R.layout.fragment_input

    override fun initViews() {
        super.initViews()
        activity?.let {
            categoriesAdapter = CategoriesAdapter{categoriesModel ->
                _categoriesModel = categoriesModel
            }
            mBinding.rcvCategories.adapter = categoriesAdapter
            categoriesAdapter?.submitData(Utils.initListCategories())
            val calendar = Calendar.getInstance()
            _year = calendar.get(Calendar.YEAR)
            _month= calendar.get(Calendar.MONTH)
            _day= calendar.get(Calendar.DAY_OF_MONTH)
            timeday= calendar.timeInMillis
            val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
            val formattedDate = formatter.format(calendar.time)
            mBinding.tvDatePicker.text = formattedDate
        }
    }

    override fun onClickViews() {
        super.onClickViews()
        activity?.let {act->
            mBinding.tvDatePicker.click {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(
                    act,
                    { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        _year = year
                        _month= month
                        _day= dayOfMonth
                        timeday= calendar.timeInMillis
                        val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
                        val formattedDate = formatter.format(calendar.time)
                        mBinding.tvDatePicker.text = formattedDate
                    },
                    _year,
                    _month,
                    _day
                )

                datePickerDialog.show()
            }
            mBinding.tvSubmit.click {
                val input = mBinding.edtExpense.text.toString().trim()
                if (input.isNotEmpty()) {
                    try {
                        val expense = input.toLong()
                        val spendingEntity = SpendingEntity(expense = expense, note = mBinding.edtNote.text.toString(), day =timeday, type = _categoriesModel.type )
                        viewModel.insert(spendingEntity)
                        mBinding.edtNote.setText("")
                        mBinding.edtExpense.setText("")
                        act.showToastById(R.string.data_has_been_saved)
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                    }
                } else {
                    act.showToastById(R.string.please_enter_expense)
                }
            }
        }
    }
}