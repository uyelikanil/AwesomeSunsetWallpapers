package com.anilyilmaz.awesomesunsetwallpapers

import android.app.Application
import com.anilyilmaz.awesomesunsetwallpapers.core.common.di.appModule
import com.anilyilmaz.awesomesunsetwallpapers.core.common.di.dispatchersModule
import com.anilyilmaz.awesomesunsetwallpapers.core.data.di.dataModule
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.di.domainModule
import com.anilyilmaz.awesomesunsetwallpapers.core.network.di.dataSourceModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.di.homeModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.di.mainModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di.wallpaperDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AswApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@AswApplication)
            modules(
                listOf(
                    appModule, dispatchersModule, dataSourceModule, dataModule, domainModule,
                    mainModule, homeModule, wallpaperDetailModule
                )
            )
        }
    }
}