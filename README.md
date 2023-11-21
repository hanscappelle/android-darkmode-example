## About this app

Simple example app to experiment with dark mode theme in app vs system setting including
webviews using `@media (prefers-color-scheme: dark)` selector.

## Dark mode using Material design

Info available from https://m2.material.io/develop/android/theming/dark

Added dependency for material starting from a simple example project
```
implementation("com.google.android.material:material:1.10.0")
```

Then changed app theme to extend from a DayNight version. See later for using a Light theme how you
can still force dark mode manually. 
```
Theme.MaterialComponents.DayNight
```

## Using a Webview

Added permission to manifest so app gets internet connection.

```
<uses-permission android:name="android.permission.INTERNET" />
```

In xml layout added a `WebView` and loaded an example URL (see web folder for example HTML).

## Support vs Force Dark mode

Info on how to support dark mode can be found on link below. Comes down to having a `DayNight` theme.
https://developer.android.com/develop/ui/views/theming/darktheme#support-dark-theme

Letting the user switch theme can be done (without having to restart the app) by simply calling the
right version, see link: 
https://developer.android.com/develop/ui/views/theming/darktheme#change-themes

```
@RequiresApi(Build.VERSION_CODES.S)
    private fun forceModeNew(mode: DarkMode) {
        val uiManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        when (mode) {
            DarkMode.LIGHT -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
            DarkMode.DARK -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
            DarkMode.SYSTEM -> uiManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO)
        }
    }
```

Info on how to force dark mode if you don't want to extend the `DayNight` theme:
https://developer.android.com/develop/ui/views/theming/darktheme#force-dark

Specific web content info:
https://developer.android.com/develop/ui/views/theming/darktheme#web-content