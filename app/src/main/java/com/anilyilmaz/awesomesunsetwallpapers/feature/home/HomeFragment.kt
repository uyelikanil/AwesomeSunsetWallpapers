package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
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
    }

    private fun setUi() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        val pagingAdapter = WallpapersAdapter(WallpapersComparator)
        binding.recyclerView.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.networkState.collectLatest {
                if (it == NetworkState.CONNECTED) {
                    viewModel.getWallpapers()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    pagingAdapter.loadStateFlow
                        .collectLatest { loadStates ->
                            binding.swiperefresh.isRefreshing =
                                loadStates.refresh is LoadState.Loading

                            binding.recyclerView.isVisible =
                                loadStates.refresh is LoadState.NotLoading

                            binding.errorLayout.root.isVisible =
                                loadStates.refresh is LoadState.Error
                        }
                }

                launch {
                    viewModel.homeUiState
                        .collectLatest { uiState ->
                            pagingAdapter.submitData(viewLifecycleOwner.lifecycle,
                                uiState.pagingData)
                        }
                }
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getWallpapers()
        }

        binding.errorLayout.errorRetryTextView.setOnClickListener { viewModel.getWallpapers() }
    }
}