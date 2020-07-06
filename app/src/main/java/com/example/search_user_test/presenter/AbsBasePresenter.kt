package com.example.search_user_test.presenter

import com.example.search_user_test.view.IBaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbsBasePresenter<VIEW : IBaseView> : IBasePresenter<VIEW> {

    open val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected var view: VIEW? = null

    fun addDisposable(disposable: Disposable?) {
        disposable?.let { mCompositeDisposable.add(it) }
    }

    fun removeDisposable(disposable: Disposable?) {
        disposable?.let { mCompositeDisposable.dispose() }
    }

}