package com.example.search_user_test.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import com.example.search_user_test.activity.MainActivity
import com.example.search_user_test.adapter.search.LocalUserSearchAdapter
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.local.LocalSearchContract
import com.example.search_user_test.presenter.local.LocalSearchPresenter
import com.example.search_user_test.ui.search.UserSearchUI
import com.example.search_user_test.utils.IGlideInterface
import com.example.search_user_test.viewholder.UserSearchItemViewHolder
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class BScreenFragment : BaseFragment<LocalSearchContract.View, LocalSearchPresenter>(),
    LocalSearchContract.View, UserSearchItemViewHolder.LocalSaveListener {

    private lateinit var mUserSearchUI: UserSearchUI

    private lateinit var mUserListAdapter: LocalUserSearchAdapter

    companion object {
        val TAG: String = BScreenFragment::class.java.simpleName
    }

    override fun onCompleted(data: Parcelable) {
    }

    override fun warningMessage(status: Int, msg: String?) {
    }

    override fun onBinding(data: Parcelable) {
    }

    override fun onCreatePresenter(): LocalSearchPresenter? = LocalSearchPresenter()

    override fun onCreateUI(): View? = with(UserSearchUI()) {
        mUserSearchUI = this
        createView(AnkoContext.create(ctx, this@BScreenFragment)).apply {
            mUserSearchUI.mSearchListView?.let {
                it.adapter = this@BScreenFragment.mUserListAdapter
                mUserListAdapter.onAttachedToRecyclerView(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserListAdapter =
            LocalUserSearchAdapter((activity as IGlideInterface).mRequestManager, this)
        mPresenter?.run {
            mAdapterModel = mUserListAdapter
            mAdapterView = mUserListAdapter
            this.mRoomDatabase = (this@BScreenFragment.activity as MainActivity).mDataBase
            getLocalUserData()
        }

        mPresenter?.mAdapterView?.notifyAdapter()

    }

    override fun insertLocalItem(userItem: UserItem) {
    }

    override fun deleteLocalItem(userItem: UserItem) {
        mPresenter?.deleteUserData(userItem)
    }
}