package com.fjjukic.birthdaysapp.base.view_model

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy


/**
 * AppVM class provides extensions for error handling and maintaining disposables
 *
 * @property compositeDisposable - disposable container which has to be cleared after clearing ViewModel
 */
abstract class AppVM : ViewModel() {
    private var compositeDisposable = CompositeDisposable()

    fun <T : Any> Observable<T>.subscribeWithErrorHandling(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {}
    ): Disposable = this.subscribeBy(
        onError = onError, onComplete = onComplete,
        onNext = onNext
    )

    fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}