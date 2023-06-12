package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import com.anilyilmaz.awesomesunsetwallpapers.BuildConfig
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import com.anilyilmaz.awesomesunsetwallpapers.databinding.FragmentWallpaperDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WallpaperDetailFragment() : Fragment() {
    private lateinit var binding: FragmentWallpaperDetailBinding
    private val viewModel by viewModels<WallpaperDetailViewModel>()
    private val args: WallpaperDetailFragmentArgs by navArgs()
    @Inject lateinit var wallpaperManager: WallpaperManager
    @Inject lateinit var setTempFileUseCase: SetTempFileUseCase
    private lateinit var imageDrawable: Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWallpaperDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()

        getImage()
    }

    private fun setUi() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(binding.buttonLayout.isVisible) {
                Navigation.findNavController(binding.root).popBackStack()
            } else {
                binding.buttonLayout.isVisible = true
            }
        }

        binding.backButton.setOnClickListener { Navigation.findNavController(it).popBackStack() }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    if(uiState is WallpaperDetailUiState.Success) {
                        loadImage(uiState.wallpaperSrc)
                        binding.photographerTextView.text = uiState.photographer
                    }

                    binding.progressBar.isVisible = uiState is WallpaperDetailUiState.Loading
                    binding.imageView.isVisible = uiState is WallpaperDetailUiState.Success
                    binding.setAsAWallpaperButton.isVisible = uiState is WallpaperDetailUiState.Success
                    binding.errorLayout.root.isVisible = uiState is WallpaperDetailUiState.Error
                }
            }
        }

        binding.imageView.setOnClickListener {
            binding.buttonLayout.isVisible = !binding.buttonLayout.isVisible
        }

        binding.setAsAWallpaperButton.setOnClickListener {
            if(::imageDrawable.isInitialized) {
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        context?.let { context ->
                            val tempImage = setTempFileUseCase.setTempImage(imageDrawable.toBitmap(),
                                Bitmap.CompressFormat.JPEG, "wallpaper")
                            val contentUri = FileProvider.getUriForFile(context,
                                BuildConfig.APPLICATION_ID + ".provider", tempImage)
                            val cropAndSetWallpaperIntent =
                                wallpaperManager.getCropAndSetWallpaperIntent(contentUri)
                            startActivity(cropAndSetWallpaperIntent)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context,
                            getString(R.string.error_occurred_while_setting_wallpaper),
                            Toast.LENGTH_SHORT).show()

                        e.printStackTrace()
                    }
                }
            }
        }

        binding.errorLayout.errorRetryTextView.setOnClickListener {
            getImage()
        }
    }

    private fun getImage() {
        if(viewModel.uiState.value !is WallpaperDetailUiState.Success) {
            val wallpaperId = args.wallpaperId
            viewModel.getWallpaper(wallpaperId)
        }
    }

    private fun loadImage(src: String) {
        context?.let {
            val imageLoader = ImageLoader(it)
            val request = ImageRequest.Builder(it)
                .data(src)
                .target { drawable ->
                    imageDrawable = drawable
                    binding.imageView.setImageDrawable(imageDrawable)
                }
                .build()
            imageLoader.enqueue(request)
        }
    }
}

