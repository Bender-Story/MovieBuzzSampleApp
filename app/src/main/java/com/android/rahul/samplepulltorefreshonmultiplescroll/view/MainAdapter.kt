package com.android.rahul.samplepulltorefreshonmultiplescroll.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rahul.samplepulltorefreshonmultiplescroll.databinding.ViewRowItemMovieBinding
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MainRowViewModel

// Main List adapter
class MainAdapter(val items: ArrayList<MainRowViewModel>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewRowItemMovieBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: ViewRowItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }

    fun add(mainRowViewModel: MainRowViewModel) {
        val position = items?.indexOf(mainRowViewModel)
        if (position!! == -1) {
            items?.add(mainRowViewModel)
            notifyItemInserted(items?.size!! - 1)
        }
    }

    fun addProgressBar(mainRowViewModel: MainRowViewModel):Boolean{
        val position = items?.indexOf(mainRowViewModel)
        if (position!! == -1) {
            add(mainRowViewModel)
            return true
        }
        return false
    }

    fun remove(mainRowViewModel: MainRowViewModel) {
        val position = items?.indexOf(mainRowViewModel)
        if (position!! > -1) {
            items?.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
