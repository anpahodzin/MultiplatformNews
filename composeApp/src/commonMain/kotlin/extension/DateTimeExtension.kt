package extension

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.formatDDMMYYYY_HHMM(): String =
    "$dayOfMonth ${month.name.lowercase()} $year $hour:$minute"

fun Instant.formatDDMMYYYY_HHMM(): String =
    toLocalDateTime(TimeZone.currentSystemDefault()).formatDDMMYYYY_HHMM()