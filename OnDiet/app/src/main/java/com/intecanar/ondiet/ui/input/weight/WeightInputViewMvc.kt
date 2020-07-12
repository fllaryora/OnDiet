package com.intecanar.ondiet.ui.input.weight

import androidx.annotation.IdRes
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.ViewMvc

interface WeightInputViewMvc : ViewMvc {
    interface Listener {
        /**
         * Callback function which will be used by our controller
         * to do its things when something happen
         */
        fun onWeightInserted(weight: Weight)
        fun onNavigateUp()

    }

    fun backToWeightScreen()
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)

}