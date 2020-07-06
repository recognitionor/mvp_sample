package com.example.search_user_test.viewholder

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.search_user_test.model.UserItem
import com.example.search_user_test.ui.search.UserSearchItemUI
import org.jetbrains.anko.AnkoContext

class UserSearchItemViewHolder(
    parent: ViewGroup,
    private val ui: UserSearchItemUI,
    private val glide: RequestManager,
    private val localSaveListener: LocalSaveListener
) :
    RecyclerView.ViewHolder(ui.createView(AnkoContext.create(parent.context, parent))) {

    interface LocalSaveListener {
        fun insertLocalItem(userItem: UserItem)

        fun deleteLocalItem(userItem: UserItem)

    }

    fun onBind(item: UserItem) {
        ui.mImageView?.let {
            glide.load(item.avatar_url)
                .apply(RequestOptions().apply {
                    //                    placeholder(R.drawable.default_man)
                })
                .thumbnail(0.1f)
                .into(it)
        }

        ui.mTextView?.text = item.login

        ui.mFavoriteCheckBox?.isChecked = item.checked

        ui.mFavoriteCheckBox?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                localSaveListener.insertLocalItem(item)
            } else {
                localSaveListener.deleteLocalItem(item)
            }
        }
    }
}