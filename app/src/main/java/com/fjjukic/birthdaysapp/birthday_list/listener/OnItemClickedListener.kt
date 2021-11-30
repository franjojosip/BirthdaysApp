package com.fjjukic.birthdaysapp.birthday_list.listener

import com.fjjukic.birthdaysapp.birthday_list.model.PersonUI

/**
 * Method for handling when item is clicked
 */
interface OnItemClickedListener {
    fun onClicked(data: PersonUI)
}