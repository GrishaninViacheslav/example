package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.users_tickers

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.R
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.Screens
import moxy.MvpPresenter
import javax.inject.Inject

class UsersTickersPresenter() : MvpPresenter<UsersTickersView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setSearchButtonHint(
            App.instance.getString(R.string.ticket_search_hint)
        )
    }

    fun searchButtonPressed() {
        router.navigateTo(Screens().tickerSearch())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}