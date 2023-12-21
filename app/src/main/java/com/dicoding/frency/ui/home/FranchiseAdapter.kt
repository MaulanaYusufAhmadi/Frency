package com.dicoding.frency.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.frency.data.remote.response.DataItem
import com.dicoding.frency.databinding.FranchiseCardBinding
import com.dicoding.frency.ui.detail.DetailActivity
import com.bumptech.glide.Glide

class FranchiseAdapter(private val franchiseList: List<DataItem>) :
    RecyclerView.Adapter<FranchiseAdapter.FranchiseViewHolder>() {

    inner class FranchiseViewHolder(val binding: FranchiseCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(franchiseData: DataItem) {
            // Bind data ke elemen UI dalam item franschise_card menggunakan ViewBinding
            binding.tvNameFranchises.text = franchiseData.franchiseName
            binding.tvCategoryFranchise.text = franchiseData.category
            binding.tvPriceFranchises.text = franchiseData.description
            // Tambahkan logika untuk menampilkan gambar jika ada

            // franchiseData.images berisi URI gambar yang diunggah ke Firebase Storage
            // Misalnya:
            Glide.with(binding.root.context).load(franchiseData.gallery.first().image).into(binding.ivFranchise)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FranchiseViewHolder {
        val binding = FranchiseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FranchiseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FranchiseViewHolder, position: Int) {
        val franchiseData = franchiseList[position]
//        val firstImageUri = franchiseData.images.firstOrNull()
        holder.bind(franchiseData)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            // Mengirim ID dokumen yang dipilih ke halaman detail menggunakan Intent
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("franchiseId", franchiseData.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return franchiseList.size
    }
}
