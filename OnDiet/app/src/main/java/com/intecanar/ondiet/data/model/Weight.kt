package com.intecanar.ondiet.data.model

import java.time.OffsetDateTime

data class Weight (
    var id: Int = 0,
    var weight: Float,
    var date: OffsetDateTime
)