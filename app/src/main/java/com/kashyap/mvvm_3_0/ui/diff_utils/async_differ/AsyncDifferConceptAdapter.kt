package com.kashyap.mvvm_3_0.ui.diff_utils.async_differ

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.RowSampleItemBinding

class AsyncDifferConceptAdapter(val delete: (UserBean) -> Unit, val edit: (UserBean) -> Unit) : RecyclerView.Adapter<AsyncDifferConceptAdapter.VHolder>() {

    val differ: AsyncListDiffer<UserBean> by lazy {
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<UserBean>() {
            override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem == newItem
            }
        })
    }

    fun addData(list: ArrayList<UserBean>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val item = RowSampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(item)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
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
}