package com.intecanar.ondiet.domain

import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster

class InsertWeightUseCase (
private val mUiThreadPoster: UiThreadPoster,
private val mBackgroundThreadPoster: BackgroundThreadPoster,
private val mFetchWeightsUseCaseSync: FetchWeightsUseCaseSync
) : BaseObservable<InsertWeightUseCase.Listener>() {

    interface Listener {
        fun onWeightSaved(newKey : Long)
    }

    fun insertWeight(weight: Weight) {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWeightsUseCaseSync.insert(weight) )
        }
    }

    private fun notifySuccess( newKey : Long) {
        mUiThreadPoster.post {
            for (listener in listeners) {
                listener.onWeightSaved(newKey)
            }
        }
    }

}