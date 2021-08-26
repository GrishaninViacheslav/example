package io.github.grishaninvyacheslav.stock_stroke_alert.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.databinding.FragmentTickerBinding
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.BackButtonListener
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker.TickerPresenter
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.ticker.TickerView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TickerFragment : MvpAppCompatFragment(), TickerView, BackButtonListener {
    private val view: FragmentTickerBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    val presenter: TickerPresenter by moxyPresenter {
        TickerPresenter(arguments?.getParcelable<Ticker>(TICKET_ARG) as Ticker).apply {
            App.instance.appComponent.inject(
                this
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        private const val TICKET_ARG = "TICKET"

        fun newInstance(ticker: Ticker) = TickerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(
                    TICKET_ARG, ticker
                )
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setTickerName(name: String) {
        view.ticketName.text = name
    }
}