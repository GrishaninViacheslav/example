package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tracker(
    @PrimaryKey(autoGenerate = true) var hash: Int? = null,
    var trackedTicker: String,
    var lastTriggerProximity : Byte,
    var triggerThreshold : Byte,
    var differenceValue: String,
    var differenceDirection: Byte,
    var differenceUnitType: String,
    var time: String,
    var notifications: String // TODO: ForeignKey
)