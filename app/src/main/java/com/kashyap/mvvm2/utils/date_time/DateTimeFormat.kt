package com.kashyap.rathod.utils.date_time

/**
 * DateTimeFormat
 * Patterns used to parse given date [DateTimeUtils] will use those pattern
 *
 * @author thunder413
 * @version 1.0
 */
object DateTimeFormat {
    /**
     * Typical MySqL/SQL dateTime format with dash as separator
     */
    const val DATE_TIME_PATTERN_1 = "yyyy-MM-dd HH:mm:ss"

    /**
     * Typical MySqL/SQL dateTime format with slash as seperator
     */
    const val DATE_TIME_PATTERN_2 = "dd/MM/yyyy HH:mm:ss"

    /**
     * Typical MySqL/SQL date format with dash as separator
     */
    const val DATE_PATTERN_1 = "yyyy-MM-dd"

    /**
     * Typical MySqL/SQL date format with slash as seperator
     */
    const val DATE_PATTERN_2 = "dd/MM/yyyy"

    /**
     * Time format full
     */
    const val TIME_PATTERN_1 = "HH:mm:ss"

    const val DATE_PATTERN_3 = "HH:mm aa, dd, MMM yyyy"
}