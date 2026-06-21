package com.anilyilmaz.awesomesunsetwallpapers.feature.favorite.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.favorite.FavoriteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}
