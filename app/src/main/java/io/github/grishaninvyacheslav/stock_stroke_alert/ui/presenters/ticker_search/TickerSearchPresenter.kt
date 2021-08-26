package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker_search

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.R
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.ITickersRepository
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.Screens
import moxy.MvpPresenter
import javax.inject.Inject

class TickerSearchPresenter() : MvpPresenter<TickerSearchView>() {
    @Inject
    lateinit var repository: ITickersRepository

    val suggestionsListPresenter: TickerSuggestionsListPresenter =
        this.TickerSuggestionsListPresenter()

    inner class TickerSuggestionsListPresenter : ITickerSuggestionsListPresenter {
        val initialSuggestions = mutableListOf<Ticker>()
        val currSuggestions = mutableListOf<Ticker>()

        override var itemClickListener: ((SuggestionItemView) -> Unit)? = null

        fun loadSuggestions() {
            val initialSuggestions = repository.getInitialSuggestions()
            suggestionsListPresenter.initialSuggestions.addAll(initialSuggestions)
            suggestionsListPresenter.currSuggestions.addAll(initialSuggestions)
        }

        override fun filterSuggestions(charText: String) {
            currSuggestions.clear()
            if (charText.isEmpty()) {
                currSuggestions.addAll(initialSuggestions)
            } else {
                currSuggestions.addAll(repository.symbolSearch(charText))
            }
        }

        override fun getCount() = currSuggestions.size

        override fun bindView(view: SuggestionItemView) {
            val suggestion = currSuggestions[view.pos]
            with(view) {
                setSymbol(suggestion.symbol)
                setFullName(suggestion.fullName)
            }
        }
    }

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setQueryHint(App.instance.getString(R.string.ticket_search_hint))
        viewState.init()
        suggestionsListPresenter.loadSuggestions()
        suggestionsListPresenter.itemClickListener = { itemView ->
            val symbol = suggestionsListPresenter.currSuggestions[itemView.pos].symbol
            val fullName = suggestionsListPresenter.currSuggestions[itemView.pos].fullName
            Log.d(
                "[MYLOG]",
                fullName
            )
            router.navigateTo(Screens().ticker(Ticker(symbol, fullName)))
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}