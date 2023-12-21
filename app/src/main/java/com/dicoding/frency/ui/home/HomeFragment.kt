package com.dicoding.frency.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.frency.R
import com.dicoding.frency.data.entity.DummyData
import com.dicoding.frency.data.entity.Franchise
import com.dicoding.frency.databinding.FragmentHomeBinding
import com.dicoding.frency.utils.ZoomOutPageTransformer
import com.dicoding.frency.utils.showMessage
import android.content.Intent
import com.dicoding.frency.ViewModelFactory
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.remote.response.DataItem
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
        binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }
        val franchiseList = mutableListOf<DataItem>()

        val layoutManager = GridLayoutManager(binding.root.context, 2)
        val recycler = binding.rvFranchise
        recycler.layoutManager = layoutManager
        val limitedList = franchiseList
        val adapterList = FranchiseListAdapter(limitedList)
        recycler.adapter = adapterList

//        adapter = FranchiseAdapter(emptyList())
//        binding.rvListStory.adapter = adapter
//        binding.rvListStory.layoutManager = LinearLayoutManager(requireContext())


        carouselHomeAdapter.submitList(DummyData.dataDummy)

        with(binding) {
            this?.carouselPager?.apply {
                adapter = carouselHomeAdapter

                val zoomOutPageTransformer = ZoomOutPageTransformer()
                setPageTransformer { page, position ->
                    zoomOutPageTransformer.transformPage(page, position)
                }

                dotsIndicator.attachTo(this)
            }
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
                    binding.pbListFranchise.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbListFranchise.visibility = View.GONE

                    if (result.data.isNotEmpty()) {
                        // Tampilkan data di RecyclerView
                        adapter = FranchiseListAdapter(result.data.first().data)
                        binding.rvFranchise.adapter = adapter
                    } else {
                        // Tampilkan pesan jika tidak ada data
                        binding.tvNoData.visibility = View.VISIBLE
                    }
                }
                is Result.Error -> {
                    // Handle error state
                    binding.pbListFranchise.visibility = View.GONE
                }
                else -> {}
            }
        }
    }
    private fun carouselItemClicked(franchise: Franchise) {
        getString(R.string.on_click_handler).showMessage(requireContext())
    }
}