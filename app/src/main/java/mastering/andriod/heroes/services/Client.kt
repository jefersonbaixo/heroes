package mastering.andriod.heroes.services

import MarvelApiService
import mastering.andriod.heroes.BuildConfig
import mastering.andriod.heroes.generateHash
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val httpClient = okhttp3.OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter("ts", System.currentTimeMillis().toString())
                .addQueryParameter("hash", generateHash())
                .build()
            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }.build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val marvelApiService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}