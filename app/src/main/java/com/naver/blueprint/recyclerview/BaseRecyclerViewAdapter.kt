package com.naver.blueprint.recyclerview

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : DiffItem<T>, VH : RecyclerView.ViewHolder> :
    ListAdapter<T, VH>(SimpleDiffItemCallback<T>())