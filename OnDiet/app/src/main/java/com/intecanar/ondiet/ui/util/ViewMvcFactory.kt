package com.intecanar.ondiet.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.intecanar.ondiet.ui.weight.WeightViewMvc
import com.intecanar.ondiet.ui.weight.WeightViewMvcImpl

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {

    fun newWeightViewMvc(parent: ViewGroup?, activity: FragmentActivity?): WeightViewMvc {
        return WeightViewMvcImpl(mLayoutInflater, parent, activity)
    }

}