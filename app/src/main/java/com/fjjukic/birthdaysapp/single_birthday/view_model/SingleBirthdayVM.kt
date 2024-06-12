package com.fjjukic.birthdaysapp.single_birthday.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fjjukic.birthdaysapp.base.live_data.SingleLiveData

/**
 * SingleBirthdayVM class provides handling for back clicking
 *
 * @property navigateUp - SingleLiveData used for emitting navigation action to View
 */
class SingleBirthdayVM : ViewModel() {
    private val _navigateUp = SingleLiveData<Boolean>()
    val navigateUp: LiveData<Boolean> = _navigateUp

    /**
     * Method for handling back clicks from View
     */
    fun handleBackClicked() {
        _navigateUp.setValue(true)
    }
}