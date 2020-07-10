package com.intecanar.ondiet.ui.weight

import androidx.annotation.IdRes
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.util.ViewMvc

// VIEW: interface
interface WeightViewMvc : ViewMvc {
    interface Listener {
        /**
         * Callback function which will be used by our controller
         * to do its things when something happen
         */
        fun onWeightClicked()
        fun onNavigateWeightScaleClicked(@IdRes rIdNavWeightInput: Int)
    }

    //fun hideMarkAsReadButton()

    fun bindWeightList(weightList: List<Weight>)

    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
}