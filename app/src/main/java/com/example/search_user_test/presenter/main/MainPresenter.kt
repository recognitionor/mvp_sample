package com.example.search_user_test.presenter.main

import com.example.search_user_test.presenter.AbsBasePresenter
import com.example.search_user_test.view.IBaseView
import io.reactivex.disposables.CompositeDisposable

class MainPresenter<VIEW : IBaseView> : AbsBasePresenter<VIEW>() {

    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun handleError(error: Throwable) {
    }


}