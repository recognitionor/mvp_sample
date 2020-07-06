package com.example.search_user_test.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.example.search_user_test.activity.BaseActivity
import com.example.search_user_test.activity.MainActivity
import com.example.search_user_test.adapter.search.ApiUserSearchAdapter
import com.example.search_user_test.http.HttpsManager
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.search.ApiSearchContract
import com.example.search_user_test.presenter.search.ApiSearchPresenter
import com.example.search_user_test.ui.search.UserSearchUI
import com.example.search_user_test.utils.IGlideInterface
import com.example.search_user_test.viewholder.UserSearchItemViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class AScreenFragment : BaseFragment<ApiSearchContract.View, ApiSearchPresenter>(),
    ApiSearchContract.View, UserSearchItemViewHolder.LocalSaveListener {

    lateinit var mUserSearchUI: UserSearchUI

    private lateinit var mUserListAdapter: ApiUserSearchAdapter

    companion object {
        val TAG: String = AScreenFragment::class.java.simpleName
    }

    override fun onCompleted(data: Parcelable) {
    }

    override fun warningMessage(status: Int, msg: String?) {
    }

    override fun onBinding(data: Parcelable) {
    }

    override fun onCreatePresenter(): ApiSearchPresenter = ApiSearchPresenter()

    fun search(query: String) {
        mPresenter?.getQueryUserData(query)
    }

    override fun onCreateUI(): View? = with(UserSearchUI()) {
        mUserSearchUI = this
        createView(AnkoContext.create(ctx, this@AScreenFragment)).apply {
            mUserSearchUI.mSearchListView?.let {
                it.adapter = this@AScreenFragment.mUserListAdapter
                mUserListAdapter.onAttachedToRecyclerView(it)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserListAdapter =
            ApiUserSearchAdapter((activity as IGlideInterface).mRequestManager, this)
        mPresenter?.run {
            mAdapterModel = mUserListAdapter
            mAdapterView = mUserListAdapter
            this.mRoomDatabase = (this@AScreenFragment.activity as MainActivity).mDataBase
//            getFirstUserData()
        }
        mPresenter?.mAdapterView?.notifyAdapter()

    }

    override fun insertLocalItem(userItem: UserItem) {
        mPresenter?.insertUserData(userItem)
    }

    override fun deleteLocalItem(userItem: UserItem) {
        mPresenter?.deleteUserData(userItem)
    }
}