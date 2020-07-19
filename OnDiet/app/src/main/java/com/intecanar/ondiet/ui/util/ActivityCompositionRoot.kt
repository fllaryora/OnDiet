package com.intecanar.ondiet.ui.util

import android.content.ContentResolver
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.intecanar.ondiet.domain.water.DeleteWaterUseCase
import com.intecanar.ondiet.domain.water.FetchAllWaterUseCase
import com.intecanar.ondiet.domain.water.FetchWaterUseCaseSync
import com.intecanar.ondiet.domain.water.InsertWaterUseCase
import com.intecanar.ondiet.domain.weight.DeleteWeightUseCase
import com.intecanar.ondiet.domain.weight.FetchAllWeightsUseCase
import com.intecanar.ondiet.domain.weight.FetchWeightsUseCaseSync
import com.intecanar.ondiet.domain.weight.InsertWeightUseCase
import com.techyourchance.threadposter.UiThreadPoster
import com.techyourchance.threadposter.BackgroundThreadPoster

class ActivityCompositionRoot(
    private val mApplicationCompositionRoot: ApplicationCompositionRoot,
    private val mActivity: AppCompatActivity
) {

    val viewMvcFactory: ViewMvcFactory
        get() = ViewMvcFactory(LayoutInflater.from(mActivity))

    private val contentResolver: ContentResolver
        get() = mActivity.contentResolver

    private val backgroundThreadPoster: BackgroundThreadPoster
        get() = mApplicationCompositionRoot.mBackgroundThreadPoster


    private val uiThreadPoster: UiThreadPoster
        get() = mApplicationCompositionRoot.mUiThreadPoster


    /// USE CASES
    //WEIGHT
    private val getFetchWeightsUseCaseSync : FetchWeightsUseCaseSync
        get() = FetchWeightsUseCaseSync()

    val fetchAllWeightsUseCase: FetchAllWeightsUseCase
        get() = FetchAllWeightsUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWeightsUseCaseSync
        )

    val deleteWeightUseCase: DeleteWeightUseCase
        get() = DeleteWeightUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWeightsUseCaseSync
        )

    val insertWeightUseCase: InsertWeightUseCase
        get() = InsertWeightUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWeightsUseCaseSync
        )

    /////WATER USE CASES

    private val getFetchWaterUseCaseSync : FetchWaterUseCaseSync
        get() = FetchWaterUseCaseSync()

    val fetchAllWaterUseCase: FetchAllWaterUseCase
        get() = FetchAllWaterUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWaterUseCaseSync
        )

    val deleteWaterUseCase: DeleteWaterUseCase
        get() = DeleteWaterUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWaterUseCaseSync
        )

    val insertWaterUseCase: InsertWaterUseCase
        get() = InsertWaterUseCase(
            uiThreadPoster,
            backgroundThreadPoster,
            getFetchWaterUseCaseSync
        )

    //////////
}
