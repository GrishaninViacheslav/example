package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.alphavantage.AlphaVantageRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.alphavantage.IAlphaVantageDataSource
import javax.inject.Singleton

@Module
class AlphaVantageRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api : IAlphaVantageDataSource): ITickersRepository = AlphaVantageRepository(api)
}