package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.DebugRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.ITickersRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repository(): ITickersRepository = DebugRepository()
}