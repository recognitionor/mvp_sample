package com.example.search_user_test.presenter.local

import android.os.Parcelable
import com.example.search_user_test.model.User
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.IBasePresenter
import com.example.search_user_test.presenter.adapter.LocalSearchAdapterContract
import com.example.search_user_test.room.AbsRoomDatabase
import com.example.search_user_test.view.IBaseView

class LocalSearchContract {
    interface View : IBaseView {
        fun onCompleted(data: Parcelable)
        fun warningMessage(status: Int, msg: String? = null)
    }

    interface Presenter : IBasePresenter<View> {

        var mUserItemList: List<UserItem>?

        var mRoomDatabase: AbsRoomDatabase?

        var mAdapterModel: LocalSearchAdapterContract.Model?

        var mAdapterView: LocalSearchAdapterContract.View?

        fun handleGetLocalUserDataResponse(list: List<UserItem>)

        fun getLocalUserData(query: String = "")

        fun deleteUserData(user: UserItem)

        fun getUserData(user: UserItem)
    }
}