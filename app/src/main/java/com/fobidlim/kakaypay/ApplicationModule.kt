package com.fobidlim.kakaypay

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.fobidlim.kakaypay.libs.CurrentUser
import com.fobidlim.kakaypay.libs.CurrentUserType
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.SharedPreferenceKey
import com.fobidlim.kakaypay.libs.preferences.StringPreference
import com.fobidlim.kakaypay.libs.preferences.StringPreferenceType
import com.fobidlim.kakaypay.libs.qualifiers.AccessTokenPreference
import com.fobidlim.kakaypay.libs.qualifiers.ApplicationContext
import com.fobidlim.kakaypay.libs.qualifiers.UserPreference
import com.fobidlim.kakaypay.services.ApiClient
import com.fobidlim.kakaypay.services.ApiClientType
import com.fobidlim.kakaypay.services.ApiService
import com.fobidlim.kakaypay.services.interceptors.ApiRequestInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    @ApplicationContext
    internal fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    internal fun provideCurrentUser(
        @ApplicationContext context: Context,
        gson: Gson,
        @AccessTokenPreference accessTokenPreference: StringPreferenceType,
        @UserPreference userPreference: StringPreferenceType
    ): CurrentUserType = CurrentUser(context, gson, accessTokenPreference, userPreference)

    @Provides
    @Singleton
    internal fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    internal fun provideEnvironment(currentUser: CurrentUserType, apiClient: ApiClientType): Environment =
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
    internal fun provideOkHttpClient(
        apiRequestInterceptor: ApiRequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    internal fun provideApiRequestInterceptor(currentUser: CurrentUserType): ApiRequestInterceptor =
        ApiRequestInterceptor(currentUser)

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    @Provides
    @Singleton
    @AccessTokenPreference
    internal fun provideAccessTokenPreference(sharedPreferences: SharedPreferences): StringPreferenceType =
        StringPreference(sharedPreferences, SharedPreferenceKey.ACCESS_TOKEN)

    @Provides
    @Singleton
    @UserPreference
    internal fun provideUserPreference(sharedPreferences: SharedPreferences): StringPreferenceType =
        StringPreference(sharedPreferences, SharedPreferenceKey.USER)

    @Provides
    @Singleton
    internal fun provideSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)
}