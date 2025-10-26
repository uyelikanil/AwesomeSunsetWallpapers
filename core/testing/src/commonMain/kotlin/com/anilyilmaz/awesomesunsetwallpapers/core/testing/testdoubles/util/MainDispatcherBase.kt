package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
open class MainDispatcherBase {
    protected val scheduler = TestCoroutineScheduler()
    protected val unconfinedTestDispatcher: TestDispatcher = UnconfinedTestDispatcher(scheduler)
    protected val standardTestDispatcher: TestDispatcher = StandardTestDispatcher(scheduler)

    fun scope(testDispatcher: TestDispatcher = unconfinedTestDispatcher
    ): TestScope = TestScope(testDispatcher)
    fun installMain(testDispatcher: TestDispatcher = unconfinedTestDispatcher) =
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
    fun resetMain() = kotlinx.coroutines.Dispatchers.resetMain()
}