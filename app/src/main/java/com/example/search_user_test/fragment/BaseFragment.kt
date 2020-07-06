package com.example.search_user_test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.search_user_test.presenter.AbsBasePresenter
import com.example.search_user_test.view.IBaseView


abstract class BaseFragment<VIEW : IBaseView, P : AbsBasePresenter<VIEW>> : Fragment(), IBaseView {

    protected var mPresenter: P? = null

    abstract fun onCreatePresenter(): P?

    abstract fun onCreateUI(): View?

    protected var mBaseView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = onCreatePresenter()
        mPresenter?.attachView(this as VIEW)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateUI()
    }
}