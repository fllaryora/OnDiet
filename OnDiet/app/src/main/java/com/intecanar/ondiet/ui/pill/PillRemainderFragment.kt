package com.intecanar.ondiet.ui.pill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.intecanar.ondiet.R
import com.intecanar.ondiet.ui.util.BaseFragment

class PillRemainderFragment : BaseFragment() {

    private lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pill_remainder, container, false)

        textView = root.findViewById(R.id.text_tools)

        return root
    }

}