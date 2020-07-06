package com.example.search_user_test.presenter.main

import android.os.Parcelable
import com.example.search_user_test.presenter.IBasePresenter
import com.example.search_user_test.presenter.search.ApiSearchContract
import com.example.search_user_test.view.IBaseView

class MainContract {
    interface View : IBaseView {
        fun onCompleted(data: Parcelable)
        fun warningMessage(status: Int, msg: String? = null)
    }

    interface Presenter : IBasePresenter<ApiSearchContract.View> {
    }
}