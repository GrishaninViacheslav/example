package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class DefaultSchedulers : Schedulers {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.io()
}