package com.danish.notesapp.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.danish.notesapp.data.local.entity.Notes

class DiffCallback(private val oldList: List<Notes>, private val newList: List<Notes>) :
    DiffUtil.Callback()  {
    override fun getOldListSize(): Int {
        return  oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.id == newData.id &&
                oldData.date == newData.desc &&
                oldData.desc == newData.desc &&
                oldData.priority == newData.priority &&
                oldData.title == newData.title
    }
}