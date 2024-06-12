package com.fjjukic.birthdaysapp.base.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Extension used for filtering null values from LiveData
 */
fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(lifecycleOwner) {
        it.let(observer)
    }
}