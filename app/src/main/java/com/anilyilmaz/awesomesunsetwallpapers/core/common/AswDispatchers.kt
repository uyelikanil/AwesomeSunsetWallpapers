package com.anilyilmaz.awesomesunsetwallpapers.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val aswDispatchers: AswDispatchers)

enum class AswDispatchers {
    IO,
}