package com.anilyilmaz.awesomesunsetwallpapers

import android.app.Application
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.di.initKoin
import com.anilyilmaz.awesomesunsetwallpapers.di.appModule
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.di.domainAndroidMainModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di.platformWallpaperDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level

class AswApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }

    fun initKoinAndroid(app: Application): KoinApplication =
        initKoin(
            appDeclaration = {
                androidLogger(Level.INFO)
                androidContext(app)
            },
            platformModules = listOf(
                appModule,
                domainAndroidMainModule,
                platformWallpaperDetailModule
            )
        )
}