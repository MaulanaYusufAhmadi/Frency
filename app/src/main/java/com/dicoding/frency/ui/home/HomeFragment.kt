package com.dicoding.frency.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.frency.R
import com.dicoding.frency.databinding.FragmentHomeBinding
import com.dicoding.frency.utils.ZoomOutPageTransformer
import com.dicoding.frency.utils.showMessage
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.frency.ViewModelFactory
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.remote.response.DataItem
import com.dicoding.frency.ui.detail.DetailActivity
import com.dicoding.frency.ui.franchiselist.FranchisesListActivity
import com.dicoding.frency.ui.login.LoginActivity
import com.dicoding.frency.ui.login.LoginViewModel

class HomeFragment : Fragment() {

    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }
    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val userViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private lateinit var adapter: FranchiseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getSession().observe(viewLifecycleOwner) {
            Log.d("userId", "saveSession: ${it.token}")
        }

        binding.rvFranchise.layoutManager = GridLayoutManager(binding.root.context, 2)

        binding.btnSeeAllFranchises.setOnClickListener {
            val intent = Intent(requireContext(), FranchisesListActivity::class.java)
            intent.putExtra("allFranchises", true)
            startActivity(intent)
        }
//        val swipeRefreshLayout = binding.swipeRefreshLayout
//
//        // Tambahkan listener untuk refresh
//        swipeRefreshLayout.setOnRefreshListener {
//            // Panggil fungsi untuk memuat ulang data
//            loadData()
//        }

        // ...

        // Memuat data pertama kali ketika fragment dimuat
        loadData()

    }

    override fun onResume() {
        super.onResume()
        // Memuat data setiap kali fragment di-resume
        loadData()
    }

    private fun loadData() {
        viewModel.getAllFranchise().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    // Handle loading state
                    Log.d("inpo3", "res loading")

                    binding.pbListFranchise.visibility = View.VISIBLE
                }
                is Result.Success -> {

                    binding.pbListFranchise.visibility = View.GONE
                    val allFranchises = result.data.data
//                    val limitedList = allFranchises.subList(0, 6)
                    Log.d("inpo3", allFranchises.count().toString())

                    Log.d("inpo3", "loadData: $allFranchises")

                    adapter = FranchiseListAdapter(allFranchises)
                    binding.rvFranchise.adapter = adapter

                    carouselHomeAdapter.submitList(allFranchises)

                    with(binding) {
                        this.carouselPager.apply {
                            adapter = carouselHomeAdapter

                            val zoomOutPageTransformer = ZoomOutPageTransformer()
                            setPageTransformer { page, position ->
                                zoomOutPageTransformer.transformPage(page, position)
                            }

                            dotsIndicator.attachTo(this)
                        }
                    }
                    if (result.data.data.isNotEmpty()) {
                        // Tampilkan data di RecyclerView

                    } else {
                        // Tampilkan pesan jika tidak ada data

                        binding.tvNoData.visibility = View.VISIBLE
                    }
                }
                is Result.Error -> {
                    // Handle error state
                    Log.d("inpo3", "result err")

                    binding.pbListFranchise.visibility = View.GONE
                }
                else -> {}
            }
        }
    }

    private fun carouselItemClicked(franchise: DataItem) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("franchiseId", franchise.id)
        startActivity(intent)
    }
}