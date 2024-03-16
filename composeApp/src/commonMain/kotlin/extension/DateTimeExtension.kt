package extension

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.formatDDMMYYYY_HHMM(): String {
    val hourString = hour.toString().padStart(2, '0')
    val minuteString = minute.toString().padStart(2, '0')
    return "$dayOfMonth ${month.name.lowercase()} $year $hourString:$minuteString"
}

fun Instant.formatDDMMYYYY_HHMM(): String =
    toLocalDateTime(TimeZone.currentSystemDefault()).formatDDMMYYYY_HHMM()