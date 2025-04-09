package com.kashyap.mvvm_3_0.ui.rv_list_custom_diff

import androidx.recyclerview.widget.DiffUtil
import com.kashyap.mvvm_3_0.data.user.UserBean

class CustomDiffUtils(val oldList: List<UserBean>, val newList: List<UserBean>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}