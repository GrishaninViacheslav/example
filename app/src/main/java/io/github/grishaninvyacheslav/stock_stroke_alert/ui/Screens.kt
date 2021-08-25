package io.github.grishaninvyacheslav.stock_stroke_alert.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.IScreens
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.views.fragments.TicketsFragment

class Screens : IScreens {
    override fun tickets() = FragmentScreen { TicketsFragment.newInstance() }
}