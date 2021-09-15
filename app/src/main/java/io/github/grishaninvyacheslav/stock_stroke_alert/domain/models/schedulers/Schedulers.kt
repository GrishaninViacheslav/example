package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.schedulers

import io.reactivex.Scheduler

interface Schedulers {
    fun main(): Scheduler
    fun background(): Scheduler
}