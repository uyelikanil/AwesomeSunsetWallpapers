package com.anilyilmaz.awesomesunsetwallpapers.core.data.di

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindsGetPhotoUseCase(getPhotoUseCase: GetPhotoUseCaseImpl): GetPhotoUseCase
}