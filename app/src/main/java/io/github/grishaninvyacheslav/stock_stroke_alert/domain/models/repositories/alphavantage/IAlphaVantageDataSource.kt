package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.alphavantage

import io.github.grishaninvyacheslav.stock_stroke_alert.BuildConfig
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IAlphaVantageDataSource {
    @GET("query?function=SYMBOL_SEARCH")
    fun symbolSearch(
        @Query("keywords") keywords: String,
        @Query("apikey") apikey: String = BuildConfig.ALPHA_VANTAGE_API_KEY
    ): Single<Map<String, List<Ticker>>>
}