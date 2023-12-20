//package com.dicoding.frency.ui.detail
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.ViewModelProvider
//import com.bumptech.glide.Glide
//import com.dicoding.frency.R
//import com.dicoding.frency.ViewModelFactory
//import com.dicoding.frency.databinding.ActivityDetailBinding
//import com.google.android.material.tabs.TabLayoutMediator
//
//class DetailActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityDetailBinding
//    private lateinit var username: String
//    private lateinit var detailViewModel: DetailViewModel
////    private lateinit var detailFranchise: DetailFranchiseResponse
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
//        detailViewModel =
//            ViewModelProvider(this, factory)[DetailViewModel::class.java]
//
//        username = intent.getStringExtra("username") ?: "DefaultUsername"
//
//        detailViewModel.getGithubUserDetail(username)
//
//        detailViewModel.githubUserDetail.observe(this) {
//            when (it) {
//                is Result.Loading -> showLoading(true)
//                is Result.Error -> {
//                    showLoading(false)
//                }
//                is Result.Success -> {
//                    showLoading(false)
//                    setUserData(it.data)
//                    detailUser = it.data
//                }
//            }
//        }
//
//        setFavorite()
//
//        setViewPager()
//    }
//
//    private fun setFavorite() {
//        val ivFavorite = binding.ivFavorite
//        detailViewModel.isFavorite(username)
//        detailViewModel.isFavorite.observe(this) {
//            when (it) {
//                true -> {
//                    ivFavorite.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            ivFavorite.context,
//                            R.drawable.baseline_favorite_24
//                        )
//                    )
//                    ivFavorite.setOnClickListener {
//                        val user = FavoriteUser(detailUser.login, detailUser.avatarUrl)
//                        detailViewModel.deleteFavorite(user)
//                    }
//                }
//                false -> {
//                    ivFavorite.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            ivFavorite.context,
//                            R.drawable.baseline_favorite_border_24
//                        )
//                    )
//                    ivFavorite.setOnClickListener {
//                        val user = FavoriteUser(detailUser.login, detailUser.avatarUrl)
//                        detailViewModel.saveFavorite(user)
//                    }
//                }
//            }
//        }
//    }
//
//    private fun setUserData(userDetail: DetailUserResponse) {
//        Glide
//            .with(this)
//            .load(userDetail.avatarUrl)
//            .fitCenter()
//            .into(binding.ivProfile)
//
//        binding.tvUsername.text = userDetail.login
//        binding.tvName.text = userDetail.name
//
//        binding.tvTotalFollowers.text =
//            getString(R.string.followers_template, userDetail.followers.toString())
//        binding.tvTotalFollowing.text =
//            getString(R.string.following_template, userDetail.following.toString())
//    }
//
//    private fun setViewPager() {
//        val adapter = SectionsPagerAdapter(this, username)
//        binding.viewPager.adapter = adapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            val tabText = this.getString(TAB_TITLES[position])
//            tab.text = tabText
//        }.attach()
//    }
//
//    private fun showLoading(isLoading: Boolean) {
//        with(binding) {
//            tvUsername.visibility = if (isLoading) View.GONE else View.VISIBLE
//            tvName.visibility = if (isLoading) View.GONE else View.VISIBLE
//            tvTotalFollowers.visibility = if (isLoading) View.GONE else View.VISIBLE
//            tvTotalFollowing.visibility = if (isLoading) View.GONE else View.VISIBLE
//            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }
//
//}