package com.kashyap.mvvm_3_0.ui.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.RowSampleItemBinding

class UserPagingAdapter : PagingDataAdapter<UserBean, UserPagingAdapter.VHolder>(diff) {

    fun addData(lifecycle: Lifecycle, data: PagingData<UserBean>) {
        submitData(lifecycle, data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val item = RowSampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHolder(item)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        holder.setData(getItem(position))
    }

    inner class VHolder(val binding: RowSampleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: UserBean?) {
            binding.actorName.text = data?.firstName

            binding.root.setOnClickListener {
                Log.e("TAG", "setData: click ")
                val obj = snapshot().items.find { it.id == data?.id }?.copy()
                obj.let { it ->
                    /*val dataSet = snapshot().items.toMutableList()
                    dataSet.removeIf { it.id == data?.id }
                    CoroutineScope(Dispatchers.Main).launch {
                        submitData(PagingData.from(dataSet))
                    }*/


                    /*obj?.firstName = "Kaash name"
                    val dataSet = snapshot().items.toMutableList()
                    dataSet[snapshot().items.indexOfFirst { it.id == data?.id }] = obj!!
                    CoroutineScope(Dispatchers.Main).launch {
                        submitData(PagingData.from(dataSet))
                    }*/
                }
            }
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<UserBean>() {
            override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem == newItem
            }
        }
    }
}