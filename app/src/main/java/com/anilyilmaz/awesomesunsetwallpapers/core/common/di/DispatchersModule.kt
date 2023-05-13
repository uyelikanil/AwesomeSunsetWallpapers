package com.anilyilmaz.awesomesunsetwallpapers.core.common.di

import com.anilyilmaz.awesomesunsetwallpapers.core.common.AswDispatchers
import com.anilyilmaz.awesomesunsetwallpapers.core.common.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(AswDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}