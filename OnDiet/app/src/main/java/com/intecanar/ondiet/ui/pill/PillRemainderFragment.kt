package com.intecanar.ondiet.ui.pill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.intecanar.ondiet.R

class PillRemainderFragment : Fragment() {

    private lateinit var pillRemainderViewModel: PillRemainderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pillRemainderViewModel =
            ViewModelProviders.of(this).get(PillRemainderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pill_remainder, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        pillRemainderViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}