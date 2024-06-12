package com.fjjukic.birthdaysapp.base.utils

import android.content.res.Resources
import com.fjjukic.birthdaysapp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * DateConverter class provides formatting from server time to time used on this application
 */

object DateConverter {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    private val serverTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun convertStringToDate(date: String): LocalDate {
        return LocalDate.parse(date, dateTimeFormatter)
    }

    fun formatString(date: String): String {
        if (date.isEmpty()) return ""
        return LocalDate.parse(date, serverTimeFormatter).format(dateTimeFormatter).toString()
    }

    /**
     * Convert date to sentence
     * @param date - represent date which is compared to now()
     * @param resources - used to retrieve plural for given age
     */
    fun convertToAgeString(date: String, resources: Resources): String {
        val convertedDate = convertStringToDate(date)
        val yearsDifference = ChronoUnit.YEARS.between(convertedDate, LocalDate.now()).toInt()
        val monthsDifference = ChronoUnit.MONTHS.between(convertedDate, LocalDate.now()).toInt()
        val daysDifference = ChronoUnit.DAYS.between(convertedDate, LocalDate.now()).toInt()

        return when {
            yearsDifference > 0 -> resources.getQuantityString(
                R.plurals.quantity_years,
                yearsDifference,
                yearsDifference
            )
            monthsDifference > 0 -> resources.getQuantityString(
                R.plurals.quantity_months,
                monthsDifference,
                monthsDifference
            )
            else -> resources.getQuantityString(
                R.plurals.quantity_days,
                daysDifference,
                daysDifference
            )
        }
    }
}