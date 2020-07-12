package com.intecanar.ondiet.domain

import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster

class DeleteWeightUseCase (
private val mUiThreadPoster: UiThreadPoster,
private val mBackgroundThreadPoster: BackgroundThreadPoster,
private val mFetchWeightsUseCaseSync: FetchWeightsUseCaseSync
) : BaseObservable<DeleteWeightUseCase.Listener>() {

    interface Listener {
        fun onWeightDeleted( success: Boolean)
    }

    fun deleteWeight(weight: Weight) {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWeightsUseCaseSync.deleteWeight(weight) )
        }
    }

    private fun notifySuccess( success: Boolean) {
        mUiThreadPoster.post {
            for (listener in listeners) {
                listener.onWeightDeleted(success)
            }
        }
    }

}