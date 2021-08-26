package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticker(val symbol: String, val fullName: String): Parcelable