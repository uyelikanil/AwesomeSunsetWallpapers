package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import org.junit.Assert.assertEquals
import org.junit.Test

class GetNetworkStateUseCaseImplTest {

    private val useCase = GetNetworkStateUseCaseImpl()

    @Test
    fun `given new NetworkState is AVAILABLE and old NetworkState is LOST, when getState is called, then NetworkState should be CONNECTED` () {
        // Given
        val newState = NetworkState.AVAILABLE
        val oldState = NetworkState.LOST

        // When
        val networkState = useCase.getState(newState, oldState)

        // Then
        assertEquals(NetworkState.CONNECTED, networkState)
    }

    @Test
    fun `given isThereNetwork is false, when getState is called, then NetworkState should be UNAVAILABLE` () {
        // Given
        val isThereNetwork = false

        // When
        val networkState = useCase.getState(isThereNetwork)

        // Then
        assertEquals(NetworkState.UNAVAILABLE, networkState)
    }
}