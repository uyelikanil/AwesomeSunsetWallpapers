package com.anilyilmaz.awesomesunsetwallpapers.core.domain.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.LoadMoreSunsetPhotosUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetNetworkStateUseCase> { GetNetworkStateUseCase() }
    factory<GetSunsetPhotosUseCase> { GetSunsetPhotosUseCase(get()) }
    factory<LoadMoreSunsetPhotosUseCase> { LoadMoreSunsetPhotosUseCase(get()) }
}