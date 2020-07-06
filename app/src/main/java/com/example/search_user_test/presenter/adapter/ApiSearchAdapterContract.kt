package com.example.search_user_test.presenter.adapter

import com.example.search_user_test.model.User
import com.example.search_user_test.model.UserItem


interface ApiSearchAdapterContract {

    interface View : BaseAdapterView {
        fun checkedItem(itemPosition: Int)
    }

    interface Model {

        var data: User?

        fun setUserData(user: User)

        fun updateUserCheckStatus(userItem: List<UserItem>)

    }
}
