package io.github.grishaninvyacheslav.stock_stroke_alert.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.grishaninvyacheslav.stock_stroke_alert.App
import io.github.grishaninvyacheslav.stock_stroke_alert.databinding.FragmentTicketsBinding
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.BackButtonListener
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.tickets.TicketsPresenter
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.tickets.TicketsView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TicketsFragment : MvpAppCompatFragment(), TicketsView, BackButtonListener {
    private val view: FragmentTicketsBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    val presenter: TicketsPresenter by moxyPresenter {
        TicketsPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = view.root

    companion object {
        private const val USER_ARG = "user"

        fun newInstance() = TicketsFragment()
    }

    override fun backPressed() = presenter.backPressed()
}