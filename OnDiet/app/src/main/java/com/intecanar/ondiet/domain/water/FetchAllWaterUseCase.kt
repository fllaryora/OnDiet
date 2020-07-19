package com.intecanar.ondiet.domain.water

import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.BaseObservable
import com.techyourchance.threadposter.BackgroundThreadPoster
import com.techyourchance.threadposter.UiThreadPoster


class FetchAllWaterUseCase (
    private val mUiThreadPoster: UiThreadPoster,
    private val mBackgroundThreadPoster: BackgroundThreadPoster,
    private val mFetchWaterUseCaseSync: FetchWaterUseCaseSync
) : BaseObservable<FetchAllWaterUseCase.Listener>() {

    interface Listener {
        fun onAllWaterFetched(waterList: List<Water>)
    }

    fun fetchAllWaterIntakesForToday() {
        mBackgroundThreadPoster.post {
            notifySuccess(mFetchWaterUseCaseSync.getWaterIntakes() )
        }
    }

    private fun notifySuccess(waterList: List<Water>) {
        mUiThreadPoster.post {
            if(listeners.isEmpty()) {
                throw Exception("Can not get event")
            }
            for (listener in listeners) {
                listener.onAllWaterFetched(waterList)
            }
        }
    }

}