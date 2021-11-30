package com.fjjukic.birthdaysapp.birthday_list.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Extension for TextView which converts given boolean to show/hide view
 */
@BindingAdapter("setVisibility")
fun TextView.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}