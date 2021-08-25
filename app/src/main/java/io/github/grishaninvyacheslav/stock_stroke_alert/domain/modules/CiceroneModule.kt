package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.presenters.IScreens
import io.github.grishaninvyacheslav.stock_stroke_alert.ui.Screens
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = Screens()
}