package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util

import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
open class MainDispatcherBase {
    protected val scheduler = TestCoroutineScheduler()
    protected val dispatcher: TestDispatcher = UnconfinedTestDispatcher(scheduler)
    protected val scope = TestScope(dispatcher)

    fun installMain() = kotlinx.coroutines.Dispatchers.setMain(dispatcher)
    fun resetMain()   = kotlinx.coroutines.Dispatchers.resetMain()
}