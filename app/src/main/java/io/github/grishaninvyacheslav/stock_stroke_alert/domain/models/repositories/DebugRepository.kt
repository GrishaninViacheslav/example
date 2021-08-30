package io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories

import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.Ticker
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
}