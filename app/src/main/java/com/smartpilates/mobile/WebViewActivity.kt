package com.smartpilates.mobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class WebViewActivity : AppCompatActivity() {

    private lateinit var id:String

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        id=intent.getStringExtra(RAPOR_ID)



        val pdfLink="https://smartpilates.net/uploads/tanita/${id}.pdf"
        val url = "https://docs.google.com/gview?embedded=true&url=$pdfLink"
        webviewRapor.settings.javaScriptEnabled = true

        webviewRapor.loadUrl(url+pdfLink)


    }






    companion object {
        const val RAPOR_ID="RAPOR_ID"
    }
}
