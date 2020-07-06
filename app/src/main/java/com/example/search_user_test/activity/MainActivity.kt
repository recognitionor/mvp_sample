package com.example.search_user_test.activity

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.search_user_test.fragment.AScreenFragment
import com.example.search_user_test.fragment.BScreenFragment
import com.example.search_user_test.fragment.BaseFragment
import com.example.search_user_test.presenter.main.MainContract
import com.example.search_user_test.presenter.main.MainPresenter
import com.example.search_user_test.ui.main.MainUI
import com.example.search_user_test.utils.IGlideInterface
import com.example.search_user_test.utils.ITextChangedListener
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<MainContract.View, MainPresenter<MainContract.View>>(),
    IGlideInterface, TabLayout.OnTabSelectedListener, ITextChangedListener {

    private lateinit var mUI: MainUI

    override lateinit var mGlide: Glide

    override lateinit var mRequestManager: RequestManager

    private var mApiTab: TabLayout.Tab? = null

    private var mLocalTab: TabLayout.Tab? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createGlide(this)

        mUI.mSearchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                mUI.mFrameLayout?.id?.let {
                    (supportFragmentManager.findFragmentById(it) as AScreenFragment).search(p0.toString())
                }
            }
        })
        mUI.mTabLayout?.apply {
            mApiTab = newTab().also {
                it.text = "API"
                addTab(it)
            }
            mLocalTab = newTab().also {
                it.text = "Local"
                addTab(it)
            }
            addOnTabSelectedListener(this@MainActivity)
        }
        mUI.mFrameLayout?.id?.let {
            supportFragmentManager.beginTransaction()
                .add(it, AScreenFragment(), AScreenFragment.TAG)
                .commit()
        }
    }

    override fun onBinding(data: Parcelable) {
    }

    override fun onCreateUI(): View? = with(MainUI()) {
        mUI = this
        createView(org.jetbrains.anko.AnkoContext.create(this@MainActivity, this@MainActivity))
    }

    override fun onCreatePresenter(): MainPresenter<MainContract.View>? = MainPresenter()

    override fun isClearCacheAtActivityDestroy(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        /*Activity 가 종료될때 캐시 제거*/
        if (isClearCacheAtActivityDestroy()) {
            clearMemoryCache()
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab) {
            mLocalTab -> {
                mUI.mFrameLayout?.id?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(it, BScreenFragment(), BScreenFragment.TAG)
                        .commit()
                }
            }
            else -> {
                mUI.mFrameLayout?.id?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(it, AScreenFragment(), AScreenFragment.TAG)
                        .commit()
                }
            }
        }
    }

    override fun onTextChanged(p0: Editable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
