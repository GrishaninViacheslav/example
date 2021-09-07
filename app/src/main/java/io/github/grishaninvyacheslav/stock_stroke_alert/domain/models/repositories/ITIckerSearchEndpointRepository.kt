package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.reactivex.Single

interface ITickerSearchEndpointRepository {
    fun symbolSearch(keywords: String): Single<List<Ticker>>
}