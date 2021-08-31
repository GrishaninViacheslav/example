package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Quote
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.tickers.alphavantage.GlobalQuote
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.*

class DebugRepository : ITickersRepository {
    private val sampleData = listOf(
        Ticker("LION", "Lion Company, Inc", "Equity"),
        Ticker("TIGER", "Tiger PLC", "Equity"),
        Ticker("DOG", "Dog Holdings Ltd", "Equity"),
        Ticker("CAT", "Cat Holdings Limited", "Equity"),
        Ticker("RAT", "Rat Systems plc", "Equity"),
        Ticker("ELEPHANT", "Elephant Group Holding Ltd", "Equity"),
        Ticker("TORTOISE", "Tortoise America Strategy Port CDA USD Ser 21/1Q MNT CASH", "Equity"),
        Ticker("FOX", "Fox Msci All Country Asia Ex Japan ETF", "Equity"),
        Ticker("COW", "Cow Applications International Corp", "Equity"),
        Ticker("DONKEY", "Donkey Motor Corporation Ltd", "Equity"),
        Ticker("MONKEY", "Monkey INCOME FUND CLASS C", "Equity")
    )


    // TODO: Не понимаю как следует отлавливать исключения в symbolSearch и getInitialSuggestions.
    //             То есть как доставлять исключения дo onError observer-ов, чтобы не возникало UndeliverableException?
    //             Если убрать try-catch в symbolSearch и getInitialSuggestions, чтобы исключения доставлялись до
    //             onError observer-ов (suggestionsLoadObserver, InitialSuggestionsObserver, CurrSuggestionsObserver),
    //             то может возникнуть UndeliverableException (если например до завершения getInitialSuggestions
    //             пользователь выйдет из поиска, вызвав этим getInitialSuggestions()...dispose() в
    //             onDestroy() презентера поиска ).

    override fun symbolSearch(keywords: String): Single<List<Ticker>> = Single.fromCallable {
        try{
            //Thread.sleep(2000)
            val suggestions = mutableListOf<Ticker>()
            for (suggestion in sampleData) {
                if (suggestion.symbol.toLowerCase(Locale.getDefault())
                        .contains(keywords.toLowerCase(Locale.getDefault()))
                ) {
                    suggestions.add(suggestion)
                }
            }
            return@fromCallable suggestions.toList()
        }
        catch (e: Exception){
            return@fromCallable listOf()
        }

    }.subscribeOn(Schedulers.io())

    override fun getInitialSuggestions(): Single<List<Ticker>> = Single.fromCallable{
        try {
            //Thread.sleep(3000)
            return@fromCallable sampleData
        }
        catch (e: Exception){
            e.printStackTrace()
            return@fromCallable listOf()
        }
    }.subscribeOn(Schedulers.io())

    override fun currPrice(symbol: String): Single<GlobalQuote> {
        TODO("Not yet implemented")
    }

    override fun intradayPrice(symbol: String, interval: Interval): Single<Map<String, Quote>> {
        return Single.just(
            mapOf(
                Pair(
                    "2021-08-30 15:15:00",
                    Quote("139.1700", "139.2050", "139.1500", "139.2000", "16714")
                ),
                Pair(
                    "2021-08-30 15:20:00",
                    Quote("139.2100", "139.2700", "139.1958", "139.2275", "37012")
                ),
                Pair(
                    "2021-08-30 15:25:00",
                    Quote("139.2300", "139.2399", "139.1500", "139.1700", "23797")
                ),
                Pair(
                    "2021-08-30 15:30:00",
                    Quote("139.1600", "139.2300", "139.1600", "139.1800", "13249")
                ),
                Pair(
                    "2021-08-30 15:35:00",
                    Quote("139.1750", "139.2400", "139.1300", "139.2200", "33891")
                ),
                Pair(
                    "2021-08-30 15:40:00",
                    Quote("139.1850", "139.1937", "139.0500", "139.0900", "32379")
                ),
                Pair(
                    "2021-08-30 15:45:00",
                    Quote("139.0800", "139.0800", "139.0436", "139.0436", "37641")
                ),
                Pair(
                    "2021-08-30 15:50:00",
                    Quote("139.0450", "139.0700", "138.9550", "138.9700", "48044")
                ),
                Pair(
                    "2021-08-30 15:55:00",
                    Quote("138.9600", "139.0850", "138.9600", "139.0600", "49321")
                ),
                Pair(
                    "2021-08-30 16:00:00",
                    Quote("139.0600", "139.0800", "138.9500", "138.9800", "105501")
                ),
                Pair(
                    "2021-08-30 16:05:00",
                    Quote("138.9700", "138.9700", "138.9700", "138.9700", "10433")
                ),
                Pair(
                    "2021-08-30 16:10:00",
                    Quote("138.9800", "138.9800", "138.9700", "138.9700", "5305")
                ),
                Pair(
                    "2021-08-30 16:25:00",
                    Quote("139.2100", "139.2100", "139.2100", "139.2100", "102")
                ),
                Pair(
                    "2021-08-30 16:35:00",
                    Quote("138.8100", "138.8100", "138.8100", "138.8100", "300")
                ),
                Pair(
                    "2021-08-30 16:40:00",
                    Quote("138.8100", "138.8100", "138.8100", "138.8100", "191")
                )
            )
        ).subscribeOn(Schedulers.io())
    }
}