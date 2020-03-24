package com.intecanar.ondiet.ui.util

import java.time.format.DateTimeFormatter

object TimeHelper {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a, dd-MMM-yyyy")
}