package com.example.search_user_test.ui.main

import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import com.example.search_user_test.R
import com.example.search_user_test.activity.MainActivity
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*

class MainUI : AnkoComponent<MainActivity>, View.OnClickListener {

    var mSearchEditText: EditText? = null

    var mTabLayout: TabLayout? = null

    var mFrameLayout: FrameLayout? = null


    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            mSearchEditText = editText {


            }.lparams(matchParent, dip(60))

            mTabLayout = include<TabLayout>(R.layout.tab_layout).lparams(matchParent, dip(60))

            mFrameLayout = frameLayout {
                id = R.id.fragment_content
            }.lparams {
                width = matchParent
                weight = 1f
            }
        }
    }


    override fun onClick(v: View?) {
    }
}