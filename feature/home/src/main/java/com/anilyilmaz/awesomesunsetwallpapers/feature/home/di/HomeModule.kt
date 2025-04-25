package com.anilyilmaz.awesomesunsetwallpapers.feature.home.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}