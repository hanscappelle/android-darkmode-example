package be.hcpl.android.darkmodeexample

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val webView = findViewById<WebView>(R.id.webview)
        // TODO host elsewhere so it remains active => firebase
        webView.loadUrl("https://www.hcpl.be/api2/test.html") // see web/test.html in project

        val light = findViewById<SwitchMaterial>(R.id.option_light)
        val dark = findViewById<SwitchMaterial>(R.id.option_dark)
        val system = findViewById<SwitchMaterial>(R.id.option_system)

        // TODO check what theme is currently active
        system.isChecked = true
        // TODO toggle theme based on selected option
        light.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //light.isChecked = true
                dark.isChecked = false
                system.isChecked = false
            }
        }
        dark.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                light.isChecked = false
                //dark.isChecked = true
                system.isChecked = false
            }
        }
        system.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                light.isChecked = false
                dark.isChecked = false
                //system.isChecked = true
            }
        }
    }


}