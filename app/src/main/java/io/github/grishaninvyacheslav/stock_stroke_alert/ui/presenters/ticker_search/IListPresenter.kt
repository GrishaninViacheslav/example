package io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker_search

interface IListPresenter<V : IItemView> {
    var itemEditClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}