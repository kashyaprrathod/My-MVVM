package com.kashyap.mvvm_3_0.ui.diff_utils.custom_diff

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.RowSampleItemBinding
import com.kashyap.mvvm_3_0.ui.diff_utils.custom_diff.ListCustomDiffUtilsConceptAdapter.VHolder

class ListCustomDiffUtilsConceptAdapter(val delete: (UserBean) -> Unit, val edit: (UserBean) -> Unit) : RecyclerView.Adapter<VHolder>() {
    private val list = mutableListOf<UserBean>()

    fun addData(arlList: List<UserBean>) {
        val result = DiffUtil.calculateDiff(CustomDiffUtils(list, arlList))
        list.clear()
        list.addAll(arlList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val item = RowSampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(item)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
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