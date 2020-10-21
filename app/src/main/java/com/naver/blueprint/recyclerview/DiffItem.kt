package com.naver.blueprint.recyclerview

interface DiffItem<T> {

    fun areItemsTheSame(newItem: T): Boolean

    fun areContentsTheSame(newItem: T): Boolean

}