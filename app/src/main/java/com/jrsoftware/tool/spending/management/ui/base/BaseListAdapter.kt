package com.jrsoftware.tool.spending.management.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseListAdapter<T>.ViewHolder>(diffCallback) {

    abstract fun getItemLayout(viewType: Int): Int
    abstract fun setData(binding: ViewDataBinding, item: T, layoutPosition: Int)
    open fun onResizeViews(binding: ViewDataBinding) = Unit
    open fun onClickViews(binding: ViewDataBinding, obj: T, layoutPosition: Int) = Unit

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getItemLayout(viewType), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(private val binding: ViewDataBinding) : BaseViewHolder<T>(binding) {
        override fun bindData(obj: T) {
            onResizeViews()
            onClickViews(obj)
            setData(binding, obj, layoutPosition)
        }

        override fun onResizeViews() {
            onResizeViews(binding)
        }

        override fun onClickViews(obj: T) {
            onClickViews(binding, obj, layoutPosition)
        }
    }
}