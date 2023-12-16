package com.dicoding.frency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.frency.data.entity.Franchise
import com.dicoding.frency.databinding.ItemCarouselHomeBinding

class CarouselHomeAdapter(private val onItemClick: (Franchise) -> Unit) :
    ListAdapter<Franchise, CarouselHomeAdapter.CarouselViewHolder>(DIFF_CALLBACK) {

    inner class CarouselViewHolder(private val binding: ItemCarouselHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Franchise) {
            binding.apply {
//                itemTvGeositeHome.text = data.name
//                itemImgGeositeHome.loadGeoparkImage(data.img)
                root.setOnClickListener { onItemClick(data) }
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Franchise>() {
            override fun areItemsTheSame(oldItem: Franchise, newItem: Franchise): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Franchise, newItem: Franchise): Boolean =
                oldItem.name == newItem.name
        }
    }
}