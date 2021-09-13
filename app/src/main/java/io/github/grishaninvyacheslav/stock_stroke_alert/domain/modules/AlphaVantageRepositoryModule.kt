package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage.TickerRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage.IAlphaVantageDataSource
import javax.inject.Singleton

@Module
class AlphaVantageRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(tickerDataApi : IAlphaVantageDataSource): ITickersRepository = TickerRepository(tickerDataApi)
}