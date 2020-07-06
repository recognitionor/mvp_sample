package com.example.search_user_test.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TAG: String = BaseRecyclerViewAdapter::class.java.simpleName

        const val TYPE_PROGRESS = Integer.MIN_VALUE + 1
    }

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            else -> onCreateBasicViewHolder(parent, viewType)
        }!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val progressOffset = 1
        if (position == itemCount - progressOffset && useProgress()) {
            onBindProgressItemView(holder, position)
        } else {
            var basicPosition = position - itemCount + basicItemCount
            if (useProgress()) basicPosition += 1
            onBindBasicItemView(holder, basicPosition)
        }
    }

    override fun getItemCount(): Int {
        var itemCount = basicItemCount
        if (useProgress()) {
            itemCount += 1
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return TYPE_PROGRESS
        }

        return getBasicItemType(position)
    }

    fun resetList() = notifyDataSetChanged()


    /**
     * Progress 추가시 true
     */
    open var isVisibleProgress: Boolean = false

    open fun useProgress() = isVisibleProgress

    open fun onCreateProgressViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder? = null

    open fun onBindProgressItemView(holder: RecyclerView.ViewHolder, position: Int) {}


    abstract fun onCreateBasicViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder?
    abstract fun onBindBasicItemView(holder: RecyclerView.ViewHolder?, position: Int)


    private var basicItemCount: Int = 0

    open fun getProgressIndex(): Int {
        return itemCount - 1
    }

    fun setBasicItemCount(count: Int) {
        basicItemCount = count
    }

    fun getBasicItemCount() = basicItemCount

    open fun getBasicItemType(position: Int): Int = position
}