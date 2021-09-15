package io.github.grishaninvyacheslav.stock_stroke_alert

import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.schedulers.DefaultSchedulers
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules.DaggerApplicationComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
                withSchedulers(DefaultSchedulers())
            }.build()
}