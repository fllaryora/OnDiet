package com.intecanar.ondiet.ui.extension

/*import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import java.time.OffsetDateTime*/

/*suspend fun Context.openDateTimePicker(offsetDateTime: OffsetDateTime = OffsetDateTime.now()): OffsetDateTime =
    suspendCoroutine { continuation ->
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            //month (0-11 for compatibility with Calendar#MONTH)
            // day of the month (1-31, depending on month)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                offsetDateTime.let {
                val newOffsetDateTime =
                    offsetDateTime.withHour(hour).withMinute(minute).withYear(year)
                        //withMonth from 1 (January) to 12 (December)
                        //so I have to adapt it.
                        .withMonth((month+1))
                        //withDayOfMonth from 1 to 28-31
                        //so no need adaptation
                        .withDayOfMonth(day)
                        continuation.resume(newOffsetDateTime)
                    }
            }

            TimePickerDialog(
                this,
                timeSetListener,
                offsetDateTime.hour,
                offsetDateTime.minute,
                DateFormat.is24HourFormat(this)
            ).show()
        }

        //the initially selected month (0-11 for compatibility with Calendar#MONTH)
        //when offsetDateTime.monthValue from 1 (January) to 12 (December)
        //so I have to adapt it.
        DatePickerDialog(
            this,
            dateSetListener,
            offsetDateTime.year,
            (offsetDateTime.monthValue-1),
            offsetDateTime.dayOfMonth
        ).show()
    }*/