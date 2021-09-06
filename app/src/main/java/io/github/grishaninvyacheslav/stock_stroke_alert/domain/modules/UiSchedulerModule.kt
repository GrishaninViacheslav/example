package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class UiSchedulerModule {
    @Singleton
    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}