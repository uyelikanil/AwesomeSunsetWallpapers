package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.databinding.FragmentHomeBinding
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()

        viewModel.getWallpapers()

        sharedViewModel.networkState.observe(viewLifecycleOwner) {
            if (it == NetworkState.CONNECTED) {
                viewModel.getWallpapers()
            }
        }
    }

    private fun setUi() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        val pagingAdapter = WallpapersAdapter(WallpapersComparator)
        binding.recyclerView.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUiState
                    .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                    .collectLatest { uiState ->
                        binding.swiperefresh.isRefreshing = uiState.isLoading

                        pagingAdapter.submitData(uiState.pagingData)

                        if(uiState.error) {
                            Toast.makeText(context, getString(R.string.error),
                                Toast.LENGTH_SHORT).show()
                            viewModel.errorHandled()
                        }
                    }
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getWallpapers()
        }
    }
}