package com.example.search_user_test.presenter.adapter

import com.example.search_user_test.model.UserItem


interface LocalSearchAdapterContract {

    interface View : BaseAdapterView {
        fun notifyDeleteItemAdapter(item: UserItem)
    }

    interface Model {

        var data: List<UserItem>?

        fun setUserItemData(list: List<UserItem>)

        fun removeUserItemData(item: UserItem)

    }
}
