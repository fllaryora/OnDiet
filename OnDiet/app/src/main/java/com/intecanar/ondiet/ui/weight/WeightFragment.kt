package com.intecanar.ondiet.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.intecanar.ondiet.R

class WeightFragment : Fragment() {

    private lateinit var weightViewModelViewModel: WeightViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weightViewModelViewModel =
            ViewModelProviders.of(this).get(WeightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weight, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        weightViewModelViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}