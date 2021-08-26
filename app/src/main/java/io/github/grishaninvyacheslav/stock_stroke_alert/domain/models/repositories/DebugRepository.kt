package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import java.util.*

class DebugRepository : ITickersRepository {
    private val sampleData = mutableListOf<Ticker>(
        Ticker("LION", "Lion Company, Inc"),
        Ticker("TIGER", "Tiger PLC"),
        Ticker("DOG", "Dog Holdings Ltd"),
        Ticker("CAT", "Cat Holdings Limited"),
        Ticker("RAT", "Rat Systems plc"),
        Ticker("ELEPHANT", "Elephant Group Holding Ltd"),
        Ticker("TORTOISE", "Tortoise America Strategy Port CDA USD Ser 21/1Q MNT CASH"),
        Ticker("FOX", "Fox Msci All Country Asia Ex Japan ETF"),
        Ticker("COW", "Cow Applications International Corp"),
        Ticker("DONKEY", "Donkey Motor Corporation Ltd"),
        Ticker("MONKEY", "Monkey INCOME FUND CLASS C")
    )

    override fun symbolSearch(keywords: String): List<Ticker> {
        val suggestions = mutableListOf<Ticker>()
        for (suggestion in sampleData) {
            if (suggestion.symbol.toLowerCase(Locale.getDefault())
                    .contains(keywords.toLowerCase(Locale.getDefault()))
            ) {
                suggestions.add(suggestion)
            }
        }
        return suggestions
    }

    override fun getInitialSuggestions(): List<Ticker> {
        return sampleData
    }
}