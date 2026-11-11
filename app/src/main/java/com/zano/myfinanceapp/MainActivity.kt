package com.zano.myfinanceapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.zano.myfinanceapp.ui.theme.MyFinanceAppTheme

private const val START_URL = "https://zinwaiyan.site/finance-app/"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyFinanceAppTheme {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView,
                                    request: WebResourceRequest
                                ): Boolean {
                                    val uri = request.url
                                    return when (uri.scheme?.lowercase()) {
                                        "http", "https" -> false
                                        else -> openExternalLink(uri)
                                    }
                                }

                                @Suppress("DEPRECATION")
                                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                                    val uri = Uri.parse(url)
                                    return when (uri.scheme?.lowercase()) {
                                        "http", "https" -> false
                                        else -> openExternalLink(uri)
                                    }
                                }
                            }
                            loadUrl(START_URL)
                        }
                    }
                )
            }
        }
    }

    private fun openExternalLink(uri: Uri): Boolean {
        return try {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
            true
        } catch (_: ActivityNotFoundException) {
            true
        }
    }
}
