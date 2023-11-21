package be.hcpl.android.darkmodeexample

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val webView = findViewById<WebView>(R.id.webview)
        webView.loadUrl("https://www.google.com")
    }


}