package app.sandbox.xp.api

import app.sandbox.xp.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

data class News(
    val title: String,
    val date: String
)

interface Api {
    @GET("news")
    fun news(): Observable<List<News>>
}

object ApiClient {

    val client: Api = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create()
}