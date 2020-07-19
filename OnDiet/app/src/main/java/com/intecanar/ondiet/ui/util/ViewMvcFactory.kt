package com.intecanar.ondiet.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.intecanar.ondiet.ui.input.water.WaterIntakeInputViewMvc
import com.intecanar.ondiet.ui.input.water.WaterIntakeInputViewMvcImpl
import com.intecanar.ondiet.ui.input.weight.WeightInputViewMvc
import com.intecanar.ondiet.ui.input.weight.WeightInputViewMvcImpl
import com.intecanar.ondiet.ui.water.WaterIntakeViewMvc
import com.intecanar.ondiet.ui.water.WaterIntakeViewMvcImpl
import com.intecanar.ondiet.ui.weight.WeightViewMvc
import com.intecanar.ondiet.ui.weight.WeightViewMvcImpl

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {

    fun newWeightViewMvc(parent: ViewGroup?, activity: FragmentActivity?): WeightViewMvc {
        return WeightViewMvcImpl(mLayoutInflater, parent, activity)
    }

    fun newWeightInputViewMvc(parent: ViewGroup?): WeightInputViewMvc {
        return WeightInputViewMvcImpl(mLayoutInflater, parent)
    }

    fun newWaterIntakeViewMvc(parent: ViewGroup?, activity: FragmentActivity?): WaterIntakeViewMvc {
        return WaterIntakeViewMvcImpl(mLayoutInflater, parent, activity)
    }

    fun newWaterInputViewMvc(parent: ViewGroup?): WaterIntakeInputViewMvc {
        return WaterIntakeInputViewMvcImpl(mLayoutInflater, parent)
    }
}