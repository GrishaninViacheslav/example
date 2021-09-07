package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.alphavantage

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.ITickersRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AlphaVantageRepository(private val api: IAlphaVantageDataSource) : ITickersRepository {
    override fun symbolSearch(keywords: String): Single<List<Ticker>> =
        api.symbolSearch(keywords)
            .flatMap { bestMatchesArr -> Single.just(bestMatchesArr["bestMatches"]!!) }
            .subscribeOn(Schedulers.io())


    override fun getInitialSuggestions(): Single<List<Ticker>> = symbolSearch("a")
}