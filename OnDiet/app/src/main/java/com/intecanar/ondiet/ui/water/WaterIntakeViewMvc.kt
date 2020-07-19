package com.intecanar.ondiet.ui.water

import androidx.annotation.IdRes
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.ViewMvc

// VIEW: interface
interface WaterIntakeViewMvc : ViewMvc {
    interface Listener {
        /**
         * Callback function which will be used by our controller
         * to do its things when something happen
         */
        fun onNavigateWaterScaleClicked(@IdRes rIdNavWaterInput: Int)
        fun onWaterSelectedToDelete(water: Water)
    }

    fun bindWaterList(waterList: List<Water>)

    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)

    fun getDeleteMessage(success:Boolean):String
}