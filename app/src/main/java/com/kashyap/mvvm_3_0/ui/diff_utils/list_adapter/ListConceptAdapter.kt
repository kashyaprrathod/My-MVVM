package com.kashyap.mvvm_3_0.ui.diff_utils.list_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.RowSampleItemBinding

class ListConceptAdapter(val delete: (UserBean) -> Unit, val edit: (UserBean) -> Unit) : ListAdapter<UserBean, ListConceptAdapter.VHolder>(diffCallback) {

    fun addList(arlList: List<UserBean>) {
        Log.e("TAG", "addList: ${arlList.map { it.email }}")
        submitList(arlList)
    }

    fun updateItem(item: UserBean) {
        currentList.find { it.id == item.id }?.email = item.email
        notifyItemChanged(currentList.indexOfFirst { it.id == item.id }, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val item = RowSampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(item)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        holder.setData(currentList[position])
    }

    inner class VHolder(val binding: RowSampleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: UserBean) {
            binding.actorName.text = data.email

            binding.root.setOnClickListener {
                delete(data)
            }

            binding.ivEdit.setOnClickListener {
                edit(data)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<UserBean>() {
            override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem == newItem
            }
        }
    }
}