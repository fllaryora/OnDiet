package com.intecanar.ondiet.ui.input.water

import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.util.ViewMvc

interface WaterIntakeInputViewMvc : ViewMvc {
    interface Listener {
        /**
         * Callback function which will be used by our controller
         * to do its things when something happen
         */
        fun onWaterInserted(water: Water)
        fun onNavigateUp()

    }

    fun backToWaterScreen()
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)

}