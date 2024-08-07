package com.test.abramovich.hearthstone.presentation.dialog

import android.content.Context
import com.test.abramovich.hearthstone.R
import com.test.abramovich.hearthstone.databinding.DialogSortBinding
import com.test.abramovich.hearthstone.domain.model.SortDirection
import com.test.abramovich.hearthstone.domain.model.SortField
import com.test.abramovich.hearthstone.domain.model.SortType

class DialogSort(
    context: Context,
    private val sortType: SortType,
    private val actionCLose: (SortType) -> Unit
) :
    BaseDialog<DialogSortBinding>(DialogSortBinding::inflate, context) {

    override fun onCreate() {
        setData()
        setupListeners()
    }

    private fun setData() {
        when (sortType.field) {
            SortField.COAST -> binding.rbCoast.isChecked = true
            SortField.CLASS -> binding.rbClass.isChecked = true
        }
        when (sortType.direction) {
            SortDirection.ASCENDING -> binding.rbAccending.isChecked = true
            SortDirection.DESCENDING -> binding.rbDescending.isChecked = true
        }
    }

    private fun setupListeners() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.rgDirection.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_accending) sortType.direction = SortDirection.ASCENDING
            else sortType.direction = SortDirection.DESCENDING
        }

        binding.rgType.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_coast) sortType.field = SortField.COAST
            else sortType.field = SortField.CLASS
        }
    }

    override fun dismiss() {
        actionCLose.invoke(sortType)
        super.dismiss()
    }

}