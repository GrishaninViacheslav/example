package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.users_tickers

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.R
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage.GlobalQuote
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.trackers.ITrackersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.IScreens
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersTickersPresenter(
    private val disposables: CompositeDisposable = CompositeDisposable()
) :
    MvpPresenter<UsersTickersView>() {
    private inner class TrackedSymbolsLoadObserver : DisposableSingleObserver<List<Ticker>>() {
        override fun onSuccess(values: List<Ticker>) {
            val items = List<Triple<Ticker, GlobalQuote?, Int?>>(values.size) { index ->
                val ticker = values[index]
                disposables.add(
                    repository.currPrice(ticker.symbol).observeOn(uiScheduler)
                        .subscribeWith(TickerQuoteLoadObserver(index))
                )
                disposables.add(
                    trackersRepository.getTickerTrackersCount(ticker.symbol).observeOn(uiScheduler)
                        .subscribeWith(TickerNotificationsCountLoadObserver(index))
                )
                Triple(ticker, null, null)
            }
            usersTickersListPresenter.tickersItems.clear()
            usersTickersListPresenter.tickersItems.addAll(items)
            viewState.updateUsersTickersList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    private inner class TickerQuoteLoadObserver(private val usersTickersListIndex: Int) :
        DisposableSingleObserver<GlobalQuote>() {
        override fun onSuccess(value: GlobalQuote) {
            with(usersTickersListPresenter.tickersItems[usersTickersListIndex]) {
                usersTickersListPresenter.tickersItems[usersTickersListIndex] =
                    Triple(first, value, third)
            }
            viewState.updateUsersTickersList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    private inner class TickerNotificationsCountLoadObserver(private val usersTickersListIndex: Int) :
        DisposableSingleObserver<Int>() {
        override fun onSuccess(value: Int) {
            with(usersTickersListPresenter.tickersItems[usersTickersListIndex]) {
                usersTickersListPresenter.tickersItems[usersTickersListIndex] =
                    Triple(first, second, value)
            }
            viewState.updateUsersTickersList()
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
        }
    }

    fun loadUsersTickers() {
        disposables.add(
            trackersRepository
                .getTrackedTickers()
                // TODO: вынести Schedulers.io() в Dagger
                .observeOn(Schedulers.io())
                .subscribeWith(TrackedSymbolsLoadObserver())
        )
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var trackersRepository: ITrackersRepository

    @Inject
    lateinit var repository: ITickersRepository

    @Inject
    lateinit var uiScheduler: Scheduler

    val usersTickersListPresenter: UsersTickersListPresenter = UsersTickersListPresenter()

    class UsersTickersListPresenter : IUsersTickersListPresenter {
        val tickersItems = mutableListOf<Triple<Ticker, GlobalQuote?, Int?>>()

        override var itemClickListener: ((TickerItemView) -> Unit)? = null

        override fun getCount() = tickersItems.size

        override fun bindView(view: TickerItemView) {
            val item = tickersItems[view.pos]
            with(view) {
                setName(item.first.symbol)
                item.first.logoUrl?.let { setLogo(it) }
                item.second?.let {
                    // TODO: вынести placeholder-ы в ресурсы
                    setCurrQuote(String.format("%.2f", it.price.toDouble()))
                    setQuoteChange("${String.format("%.2f", it.changePercent.substring(0, it.changePercent.length - 1).toDouble())}%")
                }
                item.third?.let { setTrackersCount(it.toString()) }
            }
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setSearchButtonHint(App.instance.getString(R.string.ticket_search_hint))
        usersTickersListPresenter.itemClickListener = {

            router.navigateTo(screens.ticker(usersTickersListPresenter.tickersItems[it.pos].first))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun searchButtonPressed() {
        router.navigateTo(screens.tickerSearch())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}