package com.fobidlim.kakaypay.ui.dialogs

import androidx.core.content.res.TypedArrayUtils.getString
import com.fobidlim.kakaypay.R

class InstagramAuthDialog(
    private val delegate: Delegate? = null
) : Dialog() {

    interface Delegate {
        fun instagramAccessTokenReceived(token: String)
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_instagram_auth)

        web_view.apply {
            settings.setJavaScriptEnabled(true)
            loadUrl(getString(R.string.instagram_callback_url))
            setWebViewClient(object : WebViewClient() {

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (url.startsWith(getString(R.string.instagram_callback_url))) {
                        dismiss()
                        return true
                    }
                    return false
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    if (url.contains("access_token=")) {
                        val uri = Uri.EMPTY.parse(url)
                        uri.getEncodedFragment()
                            .let {
                                substring(it.lastIndexOf("=") + 1)
                            }
                            .let {
                                delegate?.instagramAccessTokenReceived(it)
                            }
                    }
                }
            }
        })
    }
}
}