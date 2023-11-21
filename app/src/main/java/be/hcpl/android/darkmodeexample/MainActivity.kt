package be.hcpl.android.darkmodeexample

import android.app.Activity
import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        val webView = findViewById<WebView>(R.id.webview)
        webView.settings.javaScriptEnabled = true // only because using angular example app
        webView.loadUrl("https://darkmode-example.web.app") // see webapp/index.html in project

        val light = findViewById<SwitchMaterial>(R.id.option_light)
        val dark = findViewById<SwitchMaterial>(R.id.option_dark)
        val system = findViewById<SwitchMaterial>(R.id.option_system)

        // check what theme is currently active
        val nightMode = getMode()
        light.isChecked = nightMode == DarkMode.LIGHT
        dark.isChecked = nightMode == DarkMode.DARK
        system.isChecked = nightMode == DarkMode.SYSTEM

        // toggle theme based on selected option
        light.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //light.isChecked = true
                dark.isChecked = false
                system.isChecked = false
                forceMode(DarkMode.LIGHT)

            }
        }
        dark.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                light.isChecked = false
                //dark.isChecked = true
                system.isChecked = false
                forceMode(DarkMode.DARK)
            }
        }
        system.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                light.isChecked = false
                dark.isChecked = false
                //system.isChecked = true
                forceMode(DarkMode.SYSTEM)
            }
        }
    }

    enum class DarkMode {
        LIGHT, DARK, SYSTEM
    }

    private fun getMode(): DarkMode {
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R)
            when (AppCompatDelegate.getDefaultNightMode()) {
                AppCompatDelegate.MODE_NIGHT_NO -> DarkMode.LIGHT
                AppCompatDelegate.MODE_NIGHT_YES -> DarkMode.DARK
                else -> DarkMode.SYSTEM
            }
        else {
            val uiManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
            when (uiManager.nightMode) {
                UiModeManager.MODE_NIGHT_NO -> DarkMode.LIGHT
                UiModeManager.MODE_NIGHT_YES -> DarkMode.DARK
                else -> DarkMode.SYSTEM
            }
        }
    }

    private fun forceMode(mode: DarkMode) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R)
            forceModeCompat(mode)
        else
            forceModeNew(mode)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun forceModeNew(mode: DarkMode) {
        val uiManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        when (mode) {
            DarkMode.LIGHT -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
            DarkMode.DARK -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
            DarkMode.SYSTEM -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO)
        }
    }

    private fun forceModeCompat(mode: DarkMode) {
        when (mode) {
            DarkMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            DarkMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            DarkMode.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

}