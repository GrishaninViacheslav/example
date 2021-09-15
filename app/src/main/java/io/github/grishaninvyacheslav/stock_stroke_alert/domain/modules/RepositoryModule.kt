package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage.TickerRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.views.MainActivity
import javax.inject.Singleton

@Module
interface RepositoryModule {
    // TODO:
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @Singleton
    @Binds
    fun bindRepository(tickerRepository: TickerRepository): ITickersRepository
}