package com.fjjukic.birthdaysapp.birthday_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fjjukic.birthdaysapp.birthday_list.listener.OnItemClickedListener
import com.fjjukic.birthdaysapp.birthday_list.model.PersonUI
import com.fjjukic.birthdaysapp.birthday_list.view_holder.BirthdayItemViewHolder
import com.fjjukic.birthdaysapp.databinding.CellBirthdayItemBinding

/**
 * RecyclerAdapter for showing list of birthday items
 *
 * @param data - data which is displayed on UI inside RecyclerView
 * @param clickListener - listener to handle click events
 */
class BirthdayListRecyclerAdapter(
    private val data: MutableList<PersonUI>,
    private val clickListener: OnItemClickedListener
): RecyclerView.Adapter<BirthdayItemViewHolder>() {

    /**
     * Function for creating ViewHolder class
     * @return BirthdayItemViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayItemViewHolder {
        return BirthdayItemViewHolder(CellBirthdayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Bind data to the item which will be showed on screen
     */
    override fun onBindViewHolder(holder: BirthdayItemViewHolder, position: Int) {
        holder.setUpData(data[position], clickListener)
    }

    override fun getItemCount(): Int = data.size

    /**
     * Set new data inside RecyclerView and notify that data is changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<PersonUI>){
        this.data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }
}