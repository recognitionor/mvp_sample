package com.example.search_user_test.ui.search

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class UserSearchUI : AnkoComponent<Fragment>, View.OnClickListener {

    var mSearchListView: RecyclerView? = null

    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        verticalLayout {
            mSearchListView = recyclerView {
                layoutManager = LinearLayoutManager(context)

            }.lparams(matchParent, matchParent)
        }
    }

    override fun onClick(v: View?) {
    }
}