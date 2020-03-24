package com.intecanar.ondiet.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "weight_table")
data class Weight (
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "date")
    val date: OffsetDateTime,
    @NonNull
    @ColumnInfo(name = "weight")
    val weight: Float
)