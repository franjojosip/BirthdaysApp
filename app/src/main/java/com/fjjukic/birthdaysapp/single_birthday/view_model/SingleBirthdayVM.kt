package com.fjjukic.birthdaysapp.single_birthday.view_model

import androidx.lifecycle.ViewModel
import com.fjjukic.birthdaysapp.base.live_data.SingleLiveData

class SingleBirthdayVM : ViewModel() {
    val navigateUp: SingleLiveData<Boolean> by lazy { SingleLiveData() }

    fun handleBackClicked() {
        navigateUp.postValue(true)
    }
}