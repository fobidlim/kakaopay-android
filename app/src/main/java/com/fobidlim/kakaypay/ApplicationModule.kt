package com.fobidlim.kakaypay

import com.fobidlim.kakaypay.libs.CurrentUser
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
class ApplicationModule(
    private val application: ThisApplication
) {

    @Provides
    @Singleton
    internal fun provideCurrentUser(gson: Gson): CurrentUser = CurrentUser(gson)

    @Provides
    @Singleton
    internal fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    internal fun provideEnvironment(currentUser: CurrentUser, apiClient: ApiClientType): Environment =
        Environment(currentUser, apiClient)

    @Provides
    @Singleton
    internal fun provideApiClientType(gson: Gson, apiService: ApiService): ApiClientType =
        ApiClient(gson, apiService)

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    internal fun provideApiRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(application.getString(R.string.instagram_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()
}