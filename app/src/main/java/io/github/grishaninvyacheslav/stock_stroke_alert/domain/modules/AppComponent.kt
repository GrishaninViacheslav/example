package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import dagger.Component
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.main.MainPresenter
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.tickets.TicketsPresenter
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.views.activity.MainActivity
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(ticketsPresenter: TicketsPresenter)
}