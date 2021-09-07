package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticker(
    @SerializedName("1. symbol")
    @Expose
    val symbol: String,

    @SerializedName("2. name")
    @Expose
    val fullName: String,

    @SerializedName("3. type")
    @Expose
    val type: String
) : Parcelable