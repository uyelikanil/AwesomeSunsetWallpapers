package com.anilyilmaz.awesomesunsetwallpapers.core.domain.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import org.koin.dsl.module

val domainAndroidMainModule = module {
    factory<SetTempFileUseCase> { SetTempFileUseCase(get()) }
}