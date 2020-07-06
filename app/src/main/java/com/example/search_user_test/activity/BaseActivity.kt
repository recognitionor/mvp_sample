package com.example.search_user_test.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.search_user_test.presenter.AbsBasePresenter
import com.example.search_user_test.room.AbsRoomDatabase
import com.example.search_user_test.view.IBaseView

abstract class BaseActivity<VIEW : IBaseView, P : AbsBasePresenter<VIEW>> : AppCompatActivity(),
    IBaseView {

    abstract fun onCreatePresenter(): P?

    abstract fun onCreateUI(): View?

    protected var mPresenter: P? = null

    protected var mBaseView: View? = null

    var mDataBase: AbsRoomDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBase = AbsRoomDatabase.getInstance(this)
        mPresenter = onCreatePresenter()
        mPresenter?.attachView(this as VIEW)
        mBaseView = onCreateUI()
        setContentView(mBaseView)
    }
}