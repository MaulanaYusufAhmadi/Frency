package com.dicoding.frency.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.frency.data.remote.response.DataItem
import com.dicoding.frency.databinding.ItemCarouselHomeBinding

class CarouselHomeAdapter(private val onItemClick: (DataItem) -> Unit) :
    ListAdapter<DataItem, CarouselHomeAdapter.CarouselViewHolder>(DIFF_CALLBACK) {

    inner class CarouselViewHolder(private val binding: ItemCarouselHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem) {
            binding.apply {
                Glide.with(binding.root)
                    .load(item.gallery.first())
                    .into(binding.ivCarousel)
                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ItemCarouselHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val franchise = getItem(position)
        if (franchise != null) {
            holder.bind(franchise)
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
                oldItem.franchiseName == newItem.franchiseName
        }
    }
}