package com.smartpilates.mobile.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View

class GlobalFunctions {
    companion object {
        // Full Screen yapacak kod satırı Navigation ve StatusBar görünmez hgale gelir
        @SuppressLint("InlinedApi")
        fun hideNavigationBar(activity: Activity){
            activity.window.decorView
                .systemUiVisibility=
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}