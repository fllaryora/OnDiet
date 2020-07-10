package com.intecanar.ondiet.data.database

import com.intecanar.ondiet.data.converter.TimeConverter
import io.objectbox.converter.PropertyConverter
import java.time.OffsetDateTime

/**
 This class should not have exists in a perfect world.
 This class exists because I do not want to contaminate Time Converter with framework stuff.
 */
class TimeConverterForObjectBox: PropertyConverter<OffsetDateTime, String> {
    override fun convertToDatabaseValue(entityProperty: OffsetDateTime?): String {
        return TimeConverter.fromOffsetDateTime(entityProperty)!!
    }

    override fun convertToEntityProperty(databaseValue: String?): OffsetDateTime {
        return TimeConverter.toOffsetDateTime(databaseValue)!!
    }

}
