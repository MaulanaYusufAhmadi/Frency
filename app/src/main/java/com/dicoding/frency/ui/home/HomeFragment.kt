package com.dicoding.frency.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.frency.R
import com.dicoding.frency.data.entity.Franchise
import com.dicoding.frency.databinding.FragmentHomeBinding
import com.dicoding.frency.ui.adapter.CarouselHomeAdapter
import com.dicoding.frency.utils.showMessage

class HomeFragment : Fragment() {

    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initData()
//        initView()
//        initAction()
//        initObservers()
//    }
//
//    fun initData() {
//        homeViewModel.getName().observe(viewLifecycleOwner) {
//            val name = it.getFirstName()
//            binding?.textView2?.text = getString(R.string.geopark_belitong_user, name)
//        }
//
//        carouselHomeAdapter.submitList(DummyData.getAllGeosites())
//
//        cardHomeAdapter.submitList(DummyData.getAllBiodiversity())
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun carouselItemClicked(franchise: Franchise) {
        getString(R.string.on_click_handler).showMessage(requireContext())
    }
}