package com.android.bizom.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerBaseAdapter : RecyclerView.Adapter<RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        getViewModel(holder, position)
    }

    override fun onViewAttachedToWindow(holder: RecyclerViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getViewModel(holder: RecyclerViewHolder, position: Int): Any?

}