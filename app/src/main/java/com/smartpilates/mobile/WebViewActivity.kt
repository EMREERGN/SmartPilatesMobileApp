package com.smartpilates.mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.smartpilates.mobile.helpers.GlobalFunctions
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    private lateinit var fullUrl:String
    private var pdfId: String? = null

    companion object{
        const val TAG="WEBVIEWACTIVITY"
        const val FULL_URL="FULL_URL"
        const val PDF_ID="PDF_ID"
    }

    override fun onBackPressed() {

        finish()
    }

    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       GlobalFunctions.hideNavigationBar(this)

        // Hide Navigation and status bar
        fullUrl=intent.getStringExtra(FULL_URL)!!
        pdfId=intent.getStringExtra(PDF_ID)

        if (fullUrl.isNotEmpty()){
            openWebView(fullUrl)
        }

    }

    private fun openWebView(fullUrl: String) {
        Log.i(TAG,fullUrl)

        webviewRapor.settings.javaScriptEnabled = true


        webviewRapor.settings.allowFileAccess = true;
        webviewRapor.settings.allowUniversalAccessFromFileURLs=true;

        webviewRapor.settings.domStorageEnabled = true
        webviewRapor.settings.databaseEnabled = true

        webviewRapor.settings.setAppCacheEnabled(true)
        webviewRapor.settings.setAppCachePath(cacheDir.absolutePath)
        webviewRapor.settings.cacheMode = WebSettings.LOAD_DEFAULT

        webviewRapor.loadUrl(fullUrl)
        webviewRapor.invalidate()
    }



}
