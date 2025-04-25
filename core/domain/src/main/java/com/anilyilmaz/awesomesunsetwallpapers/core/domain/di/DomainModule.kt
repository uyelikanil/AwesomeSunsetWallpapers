package com.anilyilmaz.awesomesunsetwallpapers.core.domain.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetNetworkStateUseCase> { GetNetworkStateUseCase() }
    factory<GetPhotoUseCase> { GetPhotoUseCase(get()) }
    factory<GetSunsetPhotosUseCase> { GetSunsetPhotosUseCase(get()) }
    factory<SetTempFileUseCase> { SetTempFileUseCase(get()) }
}