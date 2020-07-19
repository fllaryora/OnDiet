package com.intecanar.ondiet.ui.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.format.DateFormat
import android.view.View
import java.text.SimpleDateFormat
import java.util.Date
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import java.time.OffsetDateTime
import java.util.regex.Pattern

abstract class BaseViewMvc<LISTENER> : BaseObservable<LISTENER>(), ViewMvc {

    private lateinit var mRootView: View
    private val weightPattern = Pattern.compile("^[1-9][0-9]{0,2}(\\.[0-9]{0,2})?")
    private val volumePattern = Pattern.compile("^[1-9][0-9]{0,2}(\\.[0-9]{0,2})?")

    protected fun setRootView(rootView: View) {
        mRootView = rootView
    }

    override fun getRootView(): View {
        return mRootView
    }

    protected fun <T : View> findViewById(@IdRes id: Int): T {
        return getRootView().findViewById(id)
    }

    protected fun getColor(@ColorRes colorId: Int): Int {
        return mRootView.context.resources.getColor(colorId, null)
    }

    protected fun getString(@StringRes stringId: Int): String {
        return mRootView.context.resources.getString(stringId)
    }

    protected fun String.isValidWeight(): Boolean
            = this.isNotEmpty() && weightPattern.matcher(this).matches()

    protected fun String.isValidVolume(): Boolean
            = this.isNotEmpty() && volumePattern.matcher(this).matches()

    protected fun openDateTimePicker(offsetDateTime: OffsetDateTime, callback : ((OffsetDateTime) -> Unit)) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            //onDateSet function
            val timeSetListener = createCustomSetTimeListener(offsetDateTime, year, month, day, callback)
            createTimePickerDialog(offsetDateTime, timeSetListener).show()
        }
        createDatePickerDialog(offsetDateTime, dateSetListener).show()
    }

    /**
     * description of params
     * month (0-11 for compatibility with Calendar#MONTH)
     * day of the month (1-31, depending on month)
     */
    private fun createCustomSetTimeListener( offsetDateTime: OffsetDateTime,
                                             year: Int, month: Int, day: Int,
                                             callback: (OffsetDateTime) -> Unit
    ): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            //onTimeSet function
            offsetDateTime.let {
                val newOffsetDateTime =
                    offsetDateTime.withHour(hour).withMinute(minute).withYear(year)
                        //withMonth from 1 (January) to 12 (December)
                        //so I have to adapt it.
                        .withMonth((month + 1))
                        //withDayOfMonth from 1 to 28-31
                        //so no need adaptation
                        .withDayOfMonth(day)

                callback(newOffsetDateTime)
            }
        }
    }

    private fun createTimePickerDialog(offsetDateTime: OffsetDateTime,
                                       timeSetListener : TimePickerDialog.OnTimeSetListener): TimePickerDialog {
        return TimePickerDialog(
            getRootView().context,
            timeSetListener,
            offsetDateTime.hour,
            offsetDateTime.minute,
            DateFormat.is24HourFormat(getRootView().context)
        )
    }

    private fun createDatePickerDialog(offsetDateTime: OffsetDateTime,
                                       dateSetListener : DatePickerDialog.OnDateSetListener): DatePickerDialog {
        //the initially selected month (0-11 for compatibility with Calendar#MONTH)
        //when offsetDateTime.monthValue from 1 (January) to 12 (December)
        //so I have to adapt it.
        return DatePickerDialog(
            getRootView().context,
            dateSetListener,
            offsetDateTime.year,
            (offsetDateTime.monthValue-1),
            offsetDateTime.dayOfMonth
        )
    }

}