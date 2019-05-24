package com.android.rahul.samplepulltorefreshonmultiplescroll.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rahul.samplepulltorefreshonmultiplescroll.databinding.ViewRowRelatedBinding
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MoviewRelatedRowViewModel

// Related list adapter
class MovieDetailsAdapter(val items: ArrayList<MoviewRelatedRowViewModel>?) :
    RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewRowRelatedBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: ViewRowRelatedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviewRelatedRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }
}