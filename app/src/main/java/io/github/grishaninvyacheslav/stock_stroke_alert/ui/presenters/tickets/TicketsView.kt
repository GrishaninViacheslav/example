package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.tickets

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TicketsView : MvpView {

}