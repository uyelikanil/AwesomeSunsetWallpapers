package com.anilyilmaz.awesomesunsetwallpapers.core.domain.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCaseImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCaseImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsGetPhotoUseCase(getPhotoUseCase: GetPhotoUseCaseImpl): GetPhotoUseCase

    @Binds
    fun bindsGetNetworkStateUseCase(getNetworkStateUseCase: GetNetworkStateUseCaseImpl):
            GetNetworkStateUseCase

    @Binds
    fun bindsSetTempFileUseCase(setTempFileUseCase: SetTempFileUseCaseImpl):
            SetTempFileUseCase

}