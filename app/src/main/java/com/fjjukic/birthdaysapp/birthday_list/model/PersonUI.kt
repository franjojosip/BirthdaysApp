package com.fjjukic.birthdaysapp.birthday_list.model

/**
 * Model class which represents Person which is used on UI
 */
data class PersonUI(
    val initials: String,
    val name: String,
    val dateOfBirth: String,
    val isFirstRow: Boolean = false
)