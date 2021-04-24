package com.example.assesmentmitra.network.connection

import com.example.assesmentmitra.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {

    private fun okHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(interceptor)
        }

        return okHttpClient.build()
    }

    private fun okHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(interceptor)
            okHttpClient.addInterceptor(networkConnectionInterceptor)
        }

        return okHttpClient.build()
    }

    fun serviceNetworkAPI(interceptor: NetworkConnectionInterceptor): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient(interceptor))
            .build()
    }
}