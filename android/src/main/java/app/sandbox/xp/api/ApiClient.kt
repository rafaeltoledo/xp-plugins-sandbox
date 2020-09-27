package app.sandbox.xp.api

import app.sandbox.xp.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

data class NewsErrors(
    @Json(name = "user_not_authorized_error") val userNotAuthorizedError: String?
)

data class NewsEnvelope(
    val errors: NewsErrors?,
    val data: List<News>?
)

data class News(
    val title: String,
    val date: String
)

fun NewsEnvelope.mapToResponse(): Response<List<News>, NewsErrors> = run {
    Response(errors, null, null, data)
}

interface Api {
    @GET("news/{variant}")
    fun news(@Path("variant") variant: String): Observable<NewsEnvelope>
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