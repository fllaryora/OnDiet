package com.intecanar.ondiet.ui.input.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.intecanar.ondiet.R
import com.intecanar.ondiet.ui.extension.hideKeyboard
import com.intecanar.ondiet.ui.extension.openDateTimePicker
import com.intecanar.ondiet.ui.extension.validate
import com.intecanar.ondiet.ui.util.TimeHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class WeightInputFragment : Fragment() {

    private lateinit var viewModel: WeightInputViewModel

    private lateinit var cancelButton: Button
    private lateinit var acceptButton: Button
    private lateinit var dateButton: Button
    private lateinit var dateText: TextView
    private lateinit var weight: EditText
    private var datePickerJob : Job? = null
    val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
         inflater.inflate(R.layout.fragment_weight_input, container, false)

        cancelButton = root.findViewById(R.id.textButtonCancel)
        acceptButton = root.findViewById(R.id.textButtonAccept)
        dateButton = root.findViewById(R.id.textButtonChangeDate)
        dateText = root.findViewById(R.id.textViewWeighingDateValue)
        weight = root.findViewById(R.id.weightInput)

        viewModel = ViewModelProvider(this).get(WeightInputViewModel::class.java)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.dateTimeText.observe(viewLifecycleOwner, Observer {
            dateText.text = it.format(TimeHelper.formatter)
        })
    }

    override fun onStart() {
        super.onStart()
        cancelButton.setOnClickListener {
            backToWeightScreen()
        }

        acceptButton.setOnClickListener {
            if(weight.text.toString().isValidWeight()) {
                viewModel.insert(weightText = weight.text.toString())
                backToWeightScreen()
            }
        }

        dateButton.setOnClickListener {
            datePickerJob = uiScope.launch {
                viewModel.setNewDateTime(context?.openDateTimePicker())
            }
        }

        weight.validate(resources.getString(R.string.valid_weight_required)) { s -> s.isValidWeight() }

    }

    private val weightPattern = Pattern.compile("^[1-9][0-9]{0,2}(\\.[0-9]{0,2})?")

    private fun String.isValidWeight(): Boolean
            = this.isNotEmpty() && weightPattern.matcher(this).matches()

    private fun backToWeightScreen() {
        findNavController().navigate(R.id.nav_weighing)
    }

    override fun onStop() {
        super.onStop()
        cancelButton.setOnClickListener(null)
        acceptButton.setOnClickListener(null)
        dateButton.setOnClickListener(null)
        datePickerJob?.cancel()
        hideKeyboard()
    }

}
