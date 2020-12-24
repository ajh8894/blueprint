package com.naver.blueprint.recyclerview

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

abstract class DiffOffsetRecyclerViewAdapter<T : DiffItem<T>, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val asyncListDiffer = AsyncListDiffer(OffsetListUpdateCallback(), AsyncDifferConfig.Builder(SimpleDiffItemCallback<T>()).build())

    private val realItemCount: Int
        get() = asyncListDiffer.currentList.size

    private val offset: Int
        get() = getHeaderItemCount()

    open fun getHeaderItemCount(): Int = 0

    open fun getFooterItemCount(): Int = 0

    override fun getItemCount(): Int {
        return realItemCount + getHeaderItemCount() + getFooterItemCount()
    }

    fun submitList(list: List<T>?) {
        asyncListDiffer.submitList(list)
    }

    protected fun getItems(): List<T> {
        return asyncListDiffer.currentList
    }

    fun getItem(position: Int): T {
        return asyncListDiffer.currentList[position - offset]
    }

    private inner class OffsetListUpdateCallback : ListUpdateCallback {

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(offsetPosition(position), count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(offsetPosition(position), count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(offsetPosition(fromPosition), offsetPosition(toPosition))
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyItemRangeChanged(offsetPosition(position), count, payload)
        }

        private fun offsetPosition(originalPosition: Int): Int {
            return originalPosition + offset
        }

    }

}