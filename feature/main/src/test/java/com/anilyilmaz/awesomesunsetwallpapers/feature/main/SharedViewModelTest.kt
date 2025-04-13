package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SharedViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SharedViewModel
    private val getNetworkStateUseCase = GetNetworkStateUseCase()

    @Before
    fun setUp() {
        viewModel = SharedViewModel(getNetworkStateUseCase)
    }

    @Test
    fun `given NetworkState is AVAILABLE as a first value, when updateNetworkState is called, then networkState should emit NetworkState as Available` () = runTest {
        // Given
        val networkStateAvailable = NetworkState.AVAILABLE
        var networkState: NetworkState? = null

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.networkState.collect {
                networkState = it
            }
        }

        // When
        viewModel.updateNetworkState(networkStateAvailable)

        // Then
        assertEquals(networkStateAvailable, networkState)
    }

    @Test
    fun `given NetworkState is LOST as a first value and after that given NetworkState is AVAILABLE, when updateNetworkState is called, then networkState should emit NetworkState as Connected` () = runTest {
        // Given
        val networkStateLost = NetworkState.LOST
        val networkStateAvailable = NetworkState.AVAILABLE
        val networkStateConnected = NetworkState.CONNECTED
        var networkState: NetworkState? = null

        viewModel.updateNetworkState(networkStateLost)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.networkState.collect {
                networkState = it
            }
        }

        // When
        viewModel.updateNetworkState(networkStateAvailable)

        // Then
        assertEquals(networkStateConnected, networkState)
    }

    @Test
    fun `given isNetworkAvailable is true, when updateNetworkState is called, then networkState should emit NetworkState as Available` () = runTest {
        // Given
        val isNetworkAvailable = true
        val networkStateAvailable = NetworkState.AVAILABLE
        var networkState: NetworkState? = null

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.networkState.collect {
                networkState = it
            }
        }

        // When
        viewModel.updateNetworkState(isNetworkAvailable)

        // Then
        assertEquals(networkStateAvailable, networkState)
    }
}