package com.intecanar.ondiet.domain

import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster


class FetchAllWeightsUseCase (
    private val mUiThreadPoster: UiThreadPoster,
    private val mBackgroundThreadPoster: BackgroundThreadPoster,
    private val mFetchWeightsUseCaseSync: FetchWeightsUseCaseSync
) : BaseObservable<FetchAllWeightsUseCase.Listener>() {

    interface Listener {
        fun onAllWeightsFetched(weightList: List<Weight>)
    }

    fun fetchAllWeights() {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWeightsUseCaseSync.fetchWeights() )
        }
    }

    private fun notifySuccess(weightList: List<Weight>) {
        mUiThreadPoster.post {
            for (listener in listeners) {
                listener.onAllWeightsFetched(weightList)
            }
        }
    }

}