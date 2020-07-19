package com.intecanar.ondiet.data.converter

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object TimeConverter {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    private val formatterDate = DateTimeFormatter.ISO_OFFSET_DATE

    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }

    @JvmStatic
    fun fromOffsetDate(date: OffsetDateTime?): String? {
        return date?.format(formatterDate)
    }
}
