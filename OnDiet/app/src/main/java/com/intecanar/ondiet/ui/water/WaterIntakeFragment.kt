package com.intecanar.ondiet.ui.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.intecanar.ondiet.R

class WaterIntakeFragment : Fragment() {

    private lateinit var waterIntakeViewModel: WaterIntakeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        waterIntakeViewModel =
            ViewModelProviders.of(this).get(WaterIntakeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_water_intake, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        waterIntakeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}