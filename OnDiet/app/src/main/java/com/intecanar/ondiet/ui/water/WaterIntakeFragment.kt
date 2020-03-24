package com.intecanar.ondiet.ui.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intecanar.ondiet.R

class WaterIntakeFragment : Fragment() {

    private lateinit var waterIntakeViewModel: WaterIntakeViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        waterIntakeViewModel =
            ViewModelProvider(this).get(WaterIntakeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_water_intake, container, false)
        textView = root.findViewById(R.id.text_slideshow)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        waterIntakeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}