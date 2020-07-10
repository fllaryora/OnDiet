package com.intecanar.ondiet.ui.util

import android.app.Application
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster




class ApplicationCompositionRoot(private val mApplication: Application) {

    val mUiThreadPoster: UiThreadPoster
        get() = UiThreadPoster()

    val mBackgroundThreadPoster: BackgroundThreadPoster
        get() = BackgroundThreadPoster()

}