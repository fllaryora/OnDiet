package com.intecanar.ondiet.ui.input.water

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.intecanar.ondiet.R
import com.intecanar.ondiet.data.database.entities.Water
import com.intecanar.ondiet.ui.extension.validate
import com.intecanar.ondiet.ui.util.BaseViewMvc
import com.intecanar.ondiet.ui.util.TimeHelper
import java.time.OffsetDateTime

class WaterIntakeInputViewMvcImpl (layoutInflater: LayoutInflater, container: ViewGroup?)
    : BaseViewMvc<WaterIntakeInputViewMvc.Listener>(), WaterIntakeInputViewMvc {

    private var cancelButton: Button
    private var acceptButton: Button
    private var dateButton: Button
    private var dateText: TextView
    private var dateOffsetDateTime: OffsetDateTime
    private var water: EditText

    init {
        setRootView(layoutInflater.inflate(R.layout.fragment_water_intake_input, container, false))
        cancelButton = getRootView().findViewById(R.id.textButtonCancel)
        acceptButton = getRootView().findViewById(R.id.textButtonAccept)
        dateButton = getRootView().findViewById(R.id.textButtonChangeDate)
        dateText = getRootView().findViewById(R.id.textViewWeighingDateValue)
        dateOffsetDateTime = OffsetDateTime.now()
        dateText.text = dateOffsetDateTime.format(TimeHelper.formatter)
        water = getRootView().findViewById(R.id.waterInput)

        cancelButton.setOnClickListener {
            backToWaterScreen()
        }

        acceptButton.setOnClickListener {
            val volumeText = water.text.toString()
            if(volumeText.isValidVolume()) {
                val volumeFloat : Float = volumeText.toFloat()
                val water = Water( id = 0L, volume = volumeFloat, date = dateOffsetDateTime)
                for(listener in listeners){
                    listener.onWaterInserted(water)
                }
            }
        }

        dateButton.setOnClickListener {
            openDateTimePicker(dateOffsetDateTime) {
                dateOffsetDateTime = it
                dateText.text = dateOffsetDateTime.format(TimeHelper.formatter)
            }
        }

        //Add editText validator over water
        water.validate(getString(R.string.valid_water_required)) { s ->
            s.isValidVolume()
        }
    }

    override fun backToWaterScreen() {
        for(listener in listeners){
            listener.onNavigateUp()
        }
    }

}