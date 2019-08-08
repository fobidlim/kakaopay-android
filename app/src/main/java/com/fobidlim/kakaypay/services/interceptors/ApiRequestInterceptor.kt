package com.fobidlim.kakaypay.services.interceptors

import com.fobidlim.kakaypay.libs.CurrentUserType
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiRequestInterceptor(
    private val currentUser: CurrentUserType
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(request(chain.request()))
    }

    private fun request(initialRequest: Request) =
        initialRequest.newBuilder()
            .header("Accept", "application/json")
            .url(url(initialRequest.url()))
            .build()

    private fun url(initialHttpUrl: HttpUrl) =
        initialHttpUrl.newBuilder()
            .apply {
                if (currentUser.exists()) {
                    setQueryParameter("access_token", currentUser.getAccessToken())
                }
            }
            .build()
}