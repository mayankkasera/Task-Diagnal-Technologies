package com.example.task_diagnal_technologies.di.module

import android.content.Context
import com.example.task_alphawizz.di.intersepter.AuthorizationInterceptor
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRequests
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule{

    @Provides
    @Singleton
    fun getRetrofit(@Named("name") baseUrl :String, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttp(@Named("appContext") context: Context) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverRequest(retrofit: Retrofit) : RomanticComedyRequests {
        return retrofit.create(RomanticComedyRequests::class.java)
    }

}