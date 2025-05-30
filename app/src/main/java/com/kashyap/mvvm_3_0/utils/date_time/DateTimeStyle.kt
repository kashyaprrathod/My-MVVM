@file:Suppress("unused")

package com.kashyap.mvvm2.utils.date_time

/**
 * DateTimeStyle
 *
 * Defined style for parsing date into string used by [DateTimeUtils.formatWithStyle]
 * and also [DateTimeUtils.getTimeAgo]
 *
 * @author thunder413
 * @version 1.0
 */
enum class DateTimeStyle {
    /**
     * Style full e.g Tuesday, June 13, 2017
     */
    FULL,

    /**
     * Style long e.g June 13, 2017
     */
    LONG,

    /**
     * Style medium e.g Jun 13, 2017
     */
    MEDIUM,

    /**
     * Style short e.g 06/13/17
     */
    SHORT,

    /**
     * Style for ago time e.g 3h ago
     */
    AGO_SHORT_STRING,

    /**
     * Style for ago time e.g 3 hours ago
     */
    AGO_FULL_STRING
}