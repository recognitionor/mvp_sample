package com.example.search_user_test.adapter.search

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.search_user_test.adapter.BaseRecyclerViewAdapter
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.adapter.LocalSearchAdapterContract
import com.example.search_user_test.ui.search.UserSearchItemUI
import com.example.search_user_test.viewholder.UserSearchItemViewHolder

class LocalUserSearchAdapter(
    var glide: RequestManager,
    var listener: UserSearchItemViewHolder.LocalSaveListener
) : BaseRecyclerViewAdapter(),
    LocalSearchAdapterContract.Model,
    LocalSearchAdapterContract.View {

    override fun onCreateBasicViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return UserSearchItemViewHolder(parent, UserSearchItemUI(), glide, listener)
    }

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as UserSearchItemViewHolder
        val item = data?.get(position)
        item?.let {
            holder.onBind(it)
        }
    }

    override var data: List<UserItem>? = null

    override fun setUserItemData(list: List<UserItem>) {
        list.forEach { item ->
            item.checked = true
        }
        data = list
        setBasicItemCount(list.size)
    }

    override fun removeUserItemData(item: UserItem) {
        var index = -1
        data?.indexOf(item)?.let {
            index = it
        }
        if (index > -1) {
            data?.drop(index)
        }
        data?.size?.let { setBasicItemCount(it) }
    }

    override fun notifyDeleteItemAdapter(item: UserItem) {
        var index = -1
        data?.indexOf(item)?.let {
            index = it
        }
        if (index > -1) {
            notifyItemRemoved(index)
        }
    }


    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}