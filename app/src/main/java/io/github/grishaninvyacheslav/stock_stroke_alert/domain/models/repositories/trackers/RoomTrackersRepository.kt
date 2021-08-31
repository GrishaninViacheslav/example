package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.trackers

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Tracker
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RoomTrackersRepository(private val db: TrackersRoomDatabase) : ITrackersRepository {
    override fun getTickerTrackers(symbol: String) = Single.fromCallable {
        return@fromCallable db.trackersDao.getTickerTrackers(symbol)
    }.subscribeOn(Schedulers.io())

    override fun addTracker(tracker: Tracker){
        Schedulers.io().scheduleDirect { db.trackersDao.insert(tracker) }
    }
}