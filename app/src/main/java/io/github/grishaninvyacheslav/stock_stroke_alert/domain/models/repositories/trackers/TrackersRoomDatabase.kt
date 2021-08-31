package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.trackers

import androidx.room.RoomDatabase
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Tracker

@androidx.room.Database(
    entities = [Tracker::class],
    version = 1
)
abstract class TrackersRoomDatabase : RoomDatabase() {
    abstract val trackersDao: TrackersRepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}