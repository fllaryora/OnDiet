package com.intecanar.ondiet.ui.input.weight

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.intecanar.ondiet.R
import com.intecanar.ondiet.data.database.entities.Weight
import com.intecanar.ondiet.ui.extension.validate
import com.intecanar.ondiet.ui.util.BaseViewMvc
import com.intecanar.ondiet.ui.util.TimeHelper
import java.time.OffsetDateTime
import android.text.format.DateFormat

class WeightInputViewMvcImpl (layoutInflater: LayoutInflater, container: ViewGroup?, activity: FragmentActivity?)
    : BaseViewMvc<WeightInputViewMvc.Listener>(), WeightInputViewMvc {

    private var cancelButton: Button
    private var acceptButton: Button
    private var dateButton: Button
    private var dateText: TextView
    private var dateOffsetDateTime: OffsetDateTime
    private var weight: EditText

    init {
        setRootView(layoutInflater.inflate(R.layout.fragment_weight_input, container, false))
        cancelButton = getRootView().findViewById(R.id.textButtonCancel)
        acceptButton = getRootView().findViewById(R.id.textButtonAccept)
        dateButton = getRootView().findViewById(R.id.textButtonChangeDate)
        dateText = getRootView().findViewById(R.id.textViewWeighingDateValue)
        dateOffsetDateTime = OffsetDateTime.now()
        dateText.text = dateOffsetDateTime.format(TimeHelper.formatter)
        weight = getRootView().findViewById(R.id.weightInput)

        cancelButton.setOnClickListener {
            backToWeightScreen()
        }

        acceptButton.setOnClickListener {
            val weightText = weight.text.toString()
            if(weightText.isValidWeight()) {
                val weightFloat : Float = weightText.toFloat()
                val weight = Weight( id = 0L, weight = weightFloat, date = dateOffsetDateTime)
                for(listener in listeners){
                    listener.onWeightInserted(weight)
                }
            }
        }

        dateButton.setOnClickListener {
            openDateTimePicker(dateOffsetDateTime) {
                dateOffsetDateTime = it
                dateText.text = dateOffsetDateTime.format(TimeHelper.formatter)
            }
        }

        //Add editText validator over weight
        weight.validate(getString(R.string.valid_weight_required)) { s ->
            s.isValidWeight()
        }
    }

    override fun backToWeightScreen() {
        for(listener in listeners){
            listener.onNavigateUp()
        }
    }

}