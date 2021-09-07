package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.users_tickers

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.R
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.Screens
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

class UsersTickersPresenter() : MvpPresenter<UsersTickersView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setSearchButtonHint(
            App.instance.getString(R.string.ticket_search_hint)
        )
    }

    fun searchButtonPressed() {
        router.navigateTo(screens.tickerSearch())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}