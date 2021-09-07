package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage

import android.util.Log
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Quote
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.Interval
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AlphaVantageRepository(private val api: IAlphaVantageDataSource) :
    ITickersRepository {
    override fun symbolSearch(keywords: String): Single<List<Ticker>> =
        api.symbolSearch(keywords)
            .flatMap { bestMatchesArr -> Single.just(bestMatchesArr["bestMatches"]!!) }
            .subscribeOn(Schedulers.io())


    override fun getInitialSuggestions(): Single<List<Ticker>> = symbolSearch("a")

    override fun currPrice(symbol: String) =
        api.globalQuote(symbol)
            .flatMap { value -> Single.just(value["Global Quote"]!!) }
            .subscribeOn(Schedulers.io())


    override fun intradayPrice(symbol: String, interval: Interval): Single<Map<String, Quote>> =
        api.intraday(symbol, "5min")
            .flatMap { value ->
                var intradayPriceSeries = value.timeSeries
                intradayPriceSeries = intradayPriceSeries.toSortedMap().toMap()
                Log.d(
                    "[MYLOG]",
                    "timeSeries: ${intradayPriceSeries}"
                )
                Single.just(intradayPriceSeries)
            }.subscribeOn(Schedulers.io())
}