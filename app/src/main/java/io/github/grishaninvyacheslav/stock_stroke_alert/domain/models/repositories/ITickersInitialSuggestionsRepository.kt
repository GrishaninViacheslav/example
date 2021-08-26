package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker

interface ITickersInitialSuggestionsRepository {
    fun getInitialSuggestions(): List<Ticker>
}