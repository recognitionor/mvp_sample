package com.example.search_user_test.presenter

import com.example.search_user_test.view.IBaseView

interface IBasePresenter<in VIEW : IBaseView> {
    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()

    fun handleError(error: Throwable)
}