package com.android.bizom.presentation.claims.addclaim.adapter

import androidx.databinding.Observable
import com.android.bizom.R
import com.android.bizom.data.file.container.Claimtypedetail
import com.android.bizom.databinding.ItemDropdownBinding
import com.android.bizom.databinding.ItemSingleLineAllCapsBinding
import com.android.bizom.presentation.claims.addclaim.adapter.utils.ItemDropDownVM
import com.android.bizom.presentation.claims.addclaim.adapter.utils.SingleLineTextAllCapsVM
import com.android.bizom.utils.Constants.DROP_DOWN
import com.android.bizom.utils.Constants.SINGLE_LINE_TEXT_ALL_CAPS
import com.android.bizom.utils.adapter.RecyclerBaseAdapter
import com.android.bizom.utils.adapter.RecyclerViewHolder

class DataAdapter : RecyclerBaseAdapter() {

    val formItems = mutableListOf<Claimtypedetail>()

    private var onItemClickListener: ((Claimtypedetail, Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Claimtypedetail, Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun getViewModel(holder: RecyclerViewHolder, position: Int): Any? {
        return when (holder.binding) {
            is ItemDropdownBinding -> {
                val mBinding = holder.binding as ItemDropdownBinding
                val viewModel = ItemDropDownVM(
                    formItems[position].Claimfield
                )
                mBinding.viewModel = viewModel
                mBinding.resultTxt.setOnClickListener {
                    onItemClickListener?.invoke(
                        formItems[position],
                        position
                    )
                }
                viewModel
            }
            is ItemSingleLineAllCapsBinding -> {
                val mBinding = holder.binding as ItemSingleLineAllCapsBinding
                val viewModel = SingleLineTextAllCapsVM(
                    formItems[position].Claimfield
                )
                mBinding.viewModel = viewModel
                viewModel.input.addOnPropertyChangedCallback(object :
                    Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                        formItems[position].Claimfield.input = viewModel.input.get().toString()
                    }
                })
                viewModel
            }
            else ->
                null
        }
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return when (formItems[position].Claimfield.type) {
            DROP_DOWN -> {
                R.layout.item_dropdown
            }
            SINGLE_LINE_TEXT_ALL_CAPS -> {
                R.layout.item_single_line_all_caps
            }
            else -> {
                R.layout.item_dropdown
            }
        }
    }

    override fun getItemCount(): Int = formItems.size

    fun setDataClaimTypeDetails(
        data: List<Claimtypedetail>
    ) {
        formItems.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}