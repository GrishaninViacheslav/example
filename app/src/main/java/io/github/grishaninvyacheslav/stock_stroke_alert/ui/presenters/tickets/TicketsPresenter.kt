package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.tickets

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class TicketsPresenter() : MvpPresenter<TicketsView>() {
    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}