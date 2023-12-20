package com.dicoding.frency.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.frency.data.entity.Franchise
import com.dicoding.frency.databinding.FranchiseCardBinding

class FranchiseListAdapter : ListAdapter<Franchise, FranchiseListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FranchiseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val franchiseItem = getItem(position) as Franchise
        holder.bind(franchiseItem)
    }

    class MyViewHolder(private val binding: FranchiseCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Franchise) {
            binding.tvNameFranchises.text = item.name
            binding.tvPriceFranchises.text = item.type[0]
            Glide.with(binding.root)
                .load(item.imgUrl[0])
//                .diskCacheStrategy(DiskCacheStrategy.NONE )
//                .skipMemoryCache(true)
                .into(binding.ivFranchise)


//            binding.root.setOnClickListener {
//                val context = binding.root.context
//                val intent = Intent(context, DetailUserActivity::class.java)
//                intent.putExtra(PARCEL_NAME, item)
//                context.startActivity(intent)
//            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Franchise>() {
            override fun areItemsTheSame(oldItem: Franchise, newItem: Franchise): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Franchise, newItem: Franchise): Boolean {
                return oldItem == newItem
            }
        }
        const val PARCEL_NAME = "data"
    }
}