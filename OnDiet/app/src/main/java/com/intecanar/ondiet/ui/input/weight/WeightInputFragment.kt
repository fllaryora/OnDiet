package com.intecanar.ondiet.ui.input.weight

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intecanar.ondiet.R


class WeightInputFragment : Fragment() {

    companion object {
        fun newInstance() = WeightInputFragment()
    }

    private lateinit var viewModel: WeightInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weight_input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeightInputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
