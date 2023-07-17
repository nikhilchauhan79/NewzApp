package com.example.newzapp.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatterUtils {
  fun formatDataTime(dateTxt: String): String {
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val format = DateFormat.getDateTimeInstance()
    val date = df.parse(dateTxt)
    var formattedDate: String? = null
    if (date != null) {
      formattedDate = format.format(date)
    }
    return formattedDate ?: date?.toLocaleString() ?: "null"
  }
}