package com.example.search_user_test.presenter.local

import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.AbsBasePresenter
import com.example.search_user_test.presenter.adapter.LocalSearchAdapterContract
import com.example.search_user_test.room.AbsRoomDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalSearchPresenter : AbsBasePresenter<LocalSearchContract.View>(),
    LocalSearchContract.Presenter {

    override var mUserItemList: List<UserItem>? = null

    override var mRoomDatabase: AbsRoomDatabase? = null

    override var mAdapterModel: LocalSearchAdapterContract.Model? = null

    override var mAdapterView: LocalSearchAdapterContract.View? = null

    override fun handleGetLocalUserDataResponse(list: List<UserItem>) {
        mUserItemList = list
        mAdapterModel?.setUserItemData(list)
        mAdapterView?.notifyAdapter()
    }


    override fun getLocalUserData(query: String) {
        addDisposable(
            mRoomDatabase?.userDao()?.getUser()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(this::handleGetLocalUserDataResponse, this::handleError)
        )
    }

    override fun deleteUserData(userItem: UserItem) {
        addDisposable(
            mRoomDatabase?.userDao()?.deleteUser(userItem)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    /*not working */
                }, this::handleError)
        )
        getLocalUserData()
    }

    override fun getUserData(user: UserItem) {
    }

    override fun attachView(view: LocalSearchContract.View) {
    }


    override fun detachView() {
    }

    override fun handleError(error: Throwable) {

    }

}