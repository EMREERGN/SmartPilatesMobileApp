package com.smartpilates.mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
        webviewRapor.loadUrl(fullUrl)
    }

   /* private fun checkFileAlreadyExist(id: String) {
        val pdfLink="https://smartpilates.net/uploads/tanita/${id}.pdf"
        val fullUrl = "https://docs.google.com/gview?embedded=true&url=$pdfLink"

        openViewer(id,fullUrl)

        // TODO daha sonra indirme ve gösterme işlemleri yapılacak PDF İçin
       *//* if (FileManager.checkFileIsAlreadyExists(id)){
            FileManager.openPDfFile(this,id)
        }
        else{

            openViewer(id,fullUrl)
            DownloadFile().execute(fullUrl,"$id.pdf")


        }*//*

    }*/

  /*  private fun openViewer(id: String,fullUrl:String) {

        Log.i(TAG,fullUrl)
        webviewRapor.settings.javaScriptEnabled = true
        webviewRapor.loadUrl(fullUrl)
    }*/


}
