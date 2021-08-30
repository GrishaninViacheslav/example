package io.github.grishaninvyacheslav.stock_stroke_alert.domain.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.stock_stroke_alert.domain.models.repositories.alphavantage.IAlphaVantageDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AlphaVantageApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://www.alphavantage.co/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IAlphaVantageDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IAlphaVantageDataSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()
}