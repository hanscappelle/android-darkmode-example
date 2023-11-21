## About this app

Simple example app to experiment with dark mode theme in app vs system setting including
webviews.

## Dark mode using Material design

Info available from https://m2.material.io/develop/android/theming/dark

added dependency 
```
implementation("com.google.android.material:material:1.10.0")
```

then changed app theme do extend from
```
Theme.MaterialComponents.DayNight
```

## webview

Added permission to manifest so app get internet permission

```
<uses-permission android:name="android.permission.INTERNET" />
```

## dark mode

Info on how to support dark mode 
https://developer.android.com/develop/ui/views/theming/darktheme#support-dark-theme

let the user switch
https://developer.android.com/develop/ui/views/theming/darktheme#change-themes

Info on how to force dark mode
https://developer.android.com/develop/ui/views/theming/darktheme
