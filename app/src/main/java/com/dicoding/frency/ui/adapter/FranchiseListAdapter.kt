package com.dicoding.frency.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.frency.databinding.FranchiseCardBinding
import com.dicoding.frency.ui.detail.DetailFragment

//class FranchiseListAdapter(private val franchiseList: List<ItemsItem>) :
//    RecyclerView.Adapter<FranchiseListAdapter.FranchiseViewHolder>() {
//
//    inner class FranchiseViewHolder(private val binding: FranchiseCardBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(franchise: ItemsItem) {
//
//            Glide
//                .with(itemView.context)
//                .load(franchise.avatarUrl)
//                .fitCenter()
//                .into(binding.ivFranchise)
//
//            binding.tvTitle.text = franchise.login
//            binding.tvTitle.text = franchise.login
//
//            binding.franchiseLayout.setOnClickListener {
//                val intent = Intent(itemView.context, DetailFragment::class.java)
//                intent.putExtra("username", franchise.login)
//                itemView.context.startActivity(intent)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FranchiseViewHolder {
//        return FranchiseViewHolder(
//            FranchiseCardBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return franchiseList.size
//    }
//
//    override fun onBindViewHolder(holder: FranchiseViewHolder, position: Int) {
//        val franchise = franchiseList[position]
//        holder.bind(franchise)
//    }
//}