package com.example.search_user_test.presenter.search

import android.util.Log
import com.example.search_user_test.http.HttpsManager
import com.example.search_user_test.model.User
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.AbsBasePresenter
import com.example.search_user_test.presenter.adapter.ApiSearchAdapterContract
import com.example.search_user_test.room.AbsRoomDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiSearchPresenter : AbsBasePresenter<ApiSearchContract.View>(), ApiSearchContract.Presenter {

    override var mUserData: User? = null

    override var mCheckedList: List<UserItem>? = null

    override var mRoomDatabase: AbsRoomDatabase? = null

    override var mAdapterModel: ApiSearchAdapterContract.Model? = null

    override var mAdapterView: ApiSearchAdapterContract.View? = null


    override fun getFirstUserData() {
        addDisposable(
            HttpsManager.service.searchUsers("''")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleFirstGetUserDataResponse, this::handleError)
        )
    }

    override fun getQueryUserData(query: String) {
        addDisposable(
            HttpsManager.service.searchUsers(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleFirstGetUserDataResponse, this::handleError)
        )
    }

    override fun insertUserData(userItem: UserItem) {
        addDisposable(
            mRoomDatabase?.userDao()?.insertUser(userItem)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                }, this::handleError)
        )
    }

    override fun deleteUserData(userItem: UserItem) {
        addDisposable(
            mRoomDatabase?.userDao()?.deleteUser(userItem)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                }, this::handleError)
        )
    }

    override fun getUserData(user: UserItem) {

    }

    override fun attachView(view: ApiSearchContract.View) {
    }


    override fun detachView() {
    }

    override fun handleError(error: Throwable) {

    }

    override fun handleFirstGetUserDataResponse(user: User) {
        addDisposable(
            mRoomDatabase?.userDao()?.getUser()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    mAdapterModel?.updateUserCheckStatus(it)
                }, this::handleError)
        )
        mUserData = user
        view?.onBinding(user)
        mAdapterModel?.setUserData(user)
        mAdapterView?.notifyAdapter()
    }

}