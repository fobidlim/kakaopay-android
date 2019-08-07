package com.fobidlim.kakaypay

import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.services.ApiClient
import com.fobidlim.kakaypay.services.ApiClientType
import com.fobidlim.kakaypay.services.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideEnvironment(apiClient: ApiClientType): Environment =
        Environment(apiClient)

    @Provides
    @Singleton
    internal fun provideApiClientType(apiService: ApiService): ApiClientType =
        ApiClient(apiService)

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    internal fun provideApiRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()
}