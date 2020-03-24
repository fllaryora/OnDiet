package com.intecanar.ondiet.ui.pill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intecanar.ondiet.R

class PillRemainderFragment : Fragment() {

    private lateinit var pillRemainderViewModel: PillRemainderViewModel

    private lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pillRemainderViewModel =
            ViewModelProvider(this).get(PillRemainderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pill_remainder, container, false)

        textView = root.findViewById(R.id.text_tools)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pillRemainderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}