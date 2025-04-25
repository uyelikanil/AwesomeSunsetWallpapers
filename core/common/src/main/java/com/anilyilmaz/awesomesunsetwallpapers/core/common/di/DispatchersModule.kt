package com.anilyilmaz.awesomesunsetwallpapers.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}