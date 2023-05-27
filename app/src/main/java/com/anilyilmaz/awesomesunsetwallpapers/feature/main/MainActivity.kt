package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity () {

    private lateinit var binding: ActivityMainBinding
    private val mainVewModel by viewModels<MainVewModel>()
    private val sharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setInternetConnection()

        sharedViewModel.networkState.observe(this) {
            if (it == NetworkState.CONNECTED) {
                Snackbar.make(binding.root, getString(R.string.network_is_connected),
                    Snackbar.LENGTH_SHORT).show()
            } else if (it == NetworkState.LOST || it == NetworkState.UNAVAILABLE) {
                Snackbar.make(binding.root, getString(R.string.there_is_no_network),
                    Snackbar.LENGTH_INDEFINITE).show()
            }
        }
    }

    private fun setInternetConnection () {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        mainVewModel.connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private val networkCallback =
        object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                sharedViewModel.updateNetworkState(NetworkState.AVAILABLE)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                sharedViewModel.updateNetworkState(NetworkState.LOST)

            }
    }

    override fun onStart() {
        super.onStart()

        val networkState = mainVewModel.getNetworkState()
        sharedViewModel.updateNetworkState(networkState)
    }
}

