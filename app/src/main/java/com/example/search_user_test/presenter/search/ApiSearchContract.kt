package com.example.search_user_test.presenter.search

import android.os.Parcelable
import com.example.search_user_test.model.User
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.IBasePresenter
import com.example.search_user_test.presenter.adapter.ApiSearchAdapterContract
import com.example.search_user_test.room.AbsRoomDatabase
import com.example.search_user_test.view.IBaseView

class ApiSearchContract {
    interface View : IBaseView {
        fun onCompleted(data: Parcelable)
        fun warningMessage(status: Int, msg: String? = null)
    }

    interface Presenter : IBasePresenter<View> {

        var mUserData: User?

        var mCheckedList: List<UserItem>?

        var mRoomDatabase: AbsRoomDatabase?

        var mAdapterModel: ApiSearchAdapterContract.Model?

        var mAdapterView: ApiSearchAdapterContract.View?

        fun handleFirstGetUserDataResponse(data: User)

        fun getFirstUserData()

        fun getQueryUserData(query: String)

        fun insertUserData(user: UserItem)

        fun deleteUserData(user: UserItem)

        fun getUserData(user: UserItem)

    }
}