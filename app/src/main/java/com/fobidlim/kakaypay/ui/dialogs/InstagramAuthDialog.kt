package com.fobidlim.kakaypay.ui.dialogs

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.fobidlim.kakaypay.R
import kotlinx.android.synthetic.main.dialog_instagram_auth.*
import timber.log.Timber

class InstagramAuthDialog(
    context: Context,
    private val delegate: Delegate? = null
) : Dialog(context) {

    interface Delegate {
        fun instagramAccessTokenReceived(token: String)
    }

    private val redirectUrl = context.getString(R.string.instagram_redirect_uri)
    private val clientId = context.getString(R.string.instagram_client_id)
    private val requestUrl =
        context.getString(R.string.instagram_base_url) + "oauth/authorize/?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=token&display=touch&scope=basic"


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_instagram_auth)

        web_view.apply {
            settings.javaScriptEnabled = true
            loadUrl(requestUrl)
            webViewClient = object : WebViewClient() {

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    Timber.d("shouldOverrideUrlLoading 1: url? $url")

                    if (url.startsWith(redirectUrl)) {
                        dismiss()
                        return true
                    }
                    return false
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    Timber.d("shouldOverrideUrlLoading 2: url? $url")

                    url?.let {
                        if (url.startsWith(redirectUrl)) {
                            dismiss()
                            return true
                        }
                    }
                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    Timber.d("onPageFinished: $url")

                    url?.let {
                        if (url.contains("access_token=")) {
                            val uri = Uri.parse(url)
                            uri.encodedFragment
                                ?.apply {
                                    substring(this.lastIndexOf("=") + 1)
                                        .let {
                                            delegate?.instagramAccessTokenReceived(it)
                                        }
                                }
                        }
                    }
                }
            }
        }
    }
}