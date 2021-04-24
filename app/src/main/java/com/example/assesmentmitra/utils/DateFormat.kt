package com.example.assesmentmitra.utils

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

object DateFormat {

    private val defaultLocale = Locale("in", "ID")

//    val zoneId: ZoneId = ZoneId.of("Asia/Bangkok")
//
//    fun convertTo(date: String, fromFormat: String, toFormat: String): String {
//
//        val localDate =
//            ZonedDateTime
//                .parse(
//                    date,
//                    DateTimeFormatter.ofPattern(fromFormat).withZone(zoneId)
//                )
//
//        return localDate.format(
//            DateTimeFormatter.ofPattern(toFormat).withLocale(defaultLocale)
//        )
//    }

    fun convertToNoZone(date: String, toFormat: String): String {
        var formattedDate = "";
        try {
            var dateFormat = LocalDate.parse(date)
            var formatter = DateTimeFormatter.ofPattern(toFormat, defaultLocale)
            formattedDate = dateFormat.format(formatter)
        } catch (e: Exception){
            e.printStackTrace()

        }
        return formattedDate
    }

    fun convertToTimeNoZone(date: String, toFormat: String): String {
        var formattedDate = "";
        try {
            var dateFormat = LocalTime.parse(date)
            var formatter = DateTimeFormatter.ofPattern(toFormat, defaultLocale)
            formattedDate = dateFormat.format(formatter)
        } catch (e: Exception){
            e.printStackTrace()

        }
        return formattedDate
    }

//    fun getDiffFromCurrentDate(strDate: String): Duration {
//        return Duration.between(
//            ZonedDateTime.now(),
//            ZonedDateTime.parse(
//                strDate,
//                DateTimeFormatter.ofPattern(SERVER_FORMAT).withZone(zoneId)
//            )
//        )
//    }

    const val SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val SERVER_1_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val SERVER_2_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    const val SERVER_3_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    const val SERVER_4_FORMAT = "yyyy-MM-dd"
    const val SERVER_5_FORMAT = "HH:mm:ss"

    const val DEFAULT_DATE_FORMAT = "EEEE, dd MMMM yyyy"
    const val DATE_MONTH_YEAR_FORMAT = "dd MMMM yyyy"
    const val SECOND_DATE_FORMAT = "yyyy-MM-dd"
    const val HOURS_MINUTE_FORMAT = "HH:mm"
    const val THIRD_DATE_FORMAT = "d MMMM yyyy"
    const val HOURS_MINUTE_1_FORMAT = "HH.mm"
    const val YEAR = "yyyy"
}