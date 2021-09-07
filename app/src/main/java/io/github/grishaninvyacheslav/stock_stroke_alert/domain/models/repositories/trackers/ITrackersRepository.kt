package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.trackers

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Tracker
import io.reactivex.Single

interface ITrackersRepository {
    fun getTickerTrackers(symbol: String): Single<List<Tracker>>
    fun addTracker(tracker: Tracker)
    fun delete(tracker: Tracker)
}