package database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

class DateInstantAdapter : ColumnAdapter<Instant, Long> {
    override fun decode(databaseValue: Long): Instant = Instant.fromEpochMilliseconds(databaseValue)

    override fun encode(value: Instant): Long = value.toEpochMilliseconds()
}