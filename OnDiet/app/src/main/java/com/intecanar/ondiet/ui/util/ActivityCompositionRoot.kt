package com.intecanar.ondiet.ui.util

import android.R
import android.content.ContentResolver
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.intecanar.ondiet.domain.FetchAllWeightsUseCase
import com.intecanar.ondiet.domain.FetchWeightsUseCaseSync
import com.techyourchance.threadposter.UiThreadPoster
import com.techyourchance.threadposter.BackgroundThreadPoster




class ActivityCompositionRoot(
    private val mApplicationCompositionRoot: ApplicationCompositionRoot,
    private val mActivity: AppCompatActivity
) {

    val viewMvcFactory: ViewMvcFactory
        get() = ViewMvcFactory(LayoutInflater.from(mActivity))

    val fetchAllWeightsUseCase: FetchAllWeightsUseCase
        get() = FetchAllWeightsUseCase(uiThreadPoster, backgroundThreadPoster, getFetchSmsUseCaseSync)

    private val contentResolver: ContentResolver
        get() = mActivity.contentResolver


    private val backgroundThreadPoster: BackgroundThreadPoster
        get() = mApplicationCompositionRoot.mBackgroundThreadPoster


    private val uiThreadPoster: UiThreadPoster
        get() = mApplicationCompositionRoot.mUiThreadPoster



    private val getFetchSmsUseCaseSync : FetchWeightsUseCaseSync
        get() = FetchWeightsUseCaseSync()

}
