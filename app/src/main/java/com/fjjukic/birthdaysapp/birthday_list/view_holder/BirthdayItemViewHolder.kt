package com.fjjukic.birthdaysapp.birthday_list.view_holder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fjjukic.birthdaysapp.birthday_list.listener.OnItemClickedListener
import com.fjjukic.birthdaysapp.birthday_list.model.PersonUI
import com.fjjukic.birthdaysapp.databinding.CellBirthdayItemBinding

/**
 * ViewHolder class which represents birthday item showed on UI
 * @param binding - used inside setUpData method to set data to layout
 */
class BirthdayItemViewHolder(private val binding: CellBirthdayItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setUpData(data: PersonUI, listener: OnItemClickedListener) {
        binding.tvInitials.text = data.initials
        binding.tvName.text = data.name
        binding.tvDateOfBirth.text = data.dateOfBirth
        binding.tvTopBorder.isVisible = data.isFirstRow
        binding.root.setOnClickListener {
            listener.onClicked(data)
        }
    }
}