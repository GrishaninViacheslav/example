package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import moxy.MvpPresenter
import javax.inject.Inject

class TickerPresenter(private val ticker: Ticker) : MvpPresenter<TickerView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTickerName(ticker.symbol)
    }

    fun backPressed(): Boolean {
        // TODO: Как сделать так чтобы при backPressed() происходил переход на TickerSearch?
        router.exit()
        return true
    }
}