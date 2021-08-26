package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker_search

interface SuggestionItemView : IItemView {
    fun setSymbol(symbol: String)
    fun setFullName(fullName: String)
}