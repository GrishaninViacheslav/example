package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker_search

interface ITickerSuggestionsListPresenter : IListPresenter<SuggestionItemView>{
    fun filterSuggestions(charText: String)
}