package com.intecanar.ondiet.domain.water

import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster

class InsertWaterUseCase (
private val mUiThreadPoster: UiThreadPoster,
private val mBackgroundThreadPoster: BackgroundThreadPoster,
private val mFetchWaterUseCaseSync: FetchWaterUseCaseSync
) : BaseObservable<InsertWaterUseCase.Listener>() {

    interface Listener {
        fun onWaterSaved(newKey : Long)
    }

    fun insertWaterIntake(water: Water) {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWaterUseCaseSync.insert(water) )
        }
    }

    private fun notifySuccess( newKey : Long) {
        mUiThreadPoster.post {
           if(listeners.isEmpty()) {
               throw Exception("Can not get event")
           }
            for (listener in listeners) {
                listener.onWaterSaved(newKey)
            }
        }
    }

}