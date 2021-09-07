package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TickerView : MvpView {
    fun setTickerName(name: String)
}