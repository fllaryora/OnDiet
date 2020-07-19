package com.intecanar.ondiet.domain.water

import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster

class DeleteWaterUseCase (
private val mUiThreadPoster: UiThreadPoster,
private val mBackgroundThreadPoster: BackgroundThreadPoster,
private val mFetchWaterUseCaseSync: FetchWaterUseCaseSync
) : BaseObservable<DeleteWaterUseCase.Listener>() {

    interface Listener {
        fun onWaterDeleted( success: Boolean)
    }

    fun deleteWeight(water: Water) {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWaterUseCaseSync.deleteWaterIntake(water) )
        }
    }

    private fun notifySuccess( success: Boolean) {
        mUiThreadPoster.post {
            if(listeners.isEmpty()) {
                throw Exception("Can not get event")
            }
            for (listener in listeners) {
                listener.onWaterDeleted(success)
            }
        }
    }

}