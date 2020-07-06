package com.example.search_user_test.adapter.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.search_user_test.adapter.BaseRecyclerViewAdapter
import com.example.search_user_test.model.User
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.presenter.adapter.ApiSearchAdapterContract
import com.example.search_user_test.ui.search.UserSearchItemUI
import com.example.search_user_test.viewholder.UserSearchItemViewHolder

class ApiUserSearchAdapter(
    var glide: RequestManager,
    var localSaveListener: UserSearchItemViewHolder.LocalSaveListener
) : BaseRecyclerViewAdapter(),
    ApiSearchAdapterContract.Model,
    ApiSearchAdapterContract.View {

    override fun onCreateBasicViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? {
        return UserSearchItemViewHolder(parent, UserSearchItemUI(), glide, localSaveListener)
    }

    override fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as UserSearchItemViewHolder
        val item = data?.items?.get(position)
        item?.let {
            holder.onBind(it)
        }
    }

    override var data: User? = null

    override fun setUserData(data: User) {
        this.data = data
        setBasicItemCount(data.items.size)
    }

    override fun updateUserCheckStatus(userItemList: List<UserItem>) {
        userItemList.forEachIndexed { index, userItem ->
            data?.items?.let {
                val index = it.indexOf(userItem)
                if (index > -1) {
                    it[index].checked = true
                    notifyItemChanged(index)
                }
            }
        }
    }

    override fun checkedItem(itemPosition: Int) {
        data?.items?.get(itemPosition)

    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}