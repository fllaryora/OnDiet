package com.intecanar.ondiet.data.database.entities

import com.intecanar.ondiet.data.database.TimeConverterForObjectBox
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.OffsetDateTime

@Entity
data class Water (
    @Id
    var id: Long = 0,///must be called id and must be a Long :(
    @Convert(converter = TimeConverterForObjectBox::class, dbType = String::class)
    val date: OffsetDateTime,
    val volume: Float
)