package com.anilyilmaz.awesomesunsetwallpapers.feature.main.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::SharedViewModel)
}
