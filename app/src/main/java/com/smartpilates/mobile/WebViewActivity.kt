package com.smartpilates.mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.smartpilates.mobile.helpers.DownloadFile
import com.smartpilates.mobile.helpers.FileManager
import com.smartpilates.mobile.helpers.GlobalFunctions
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    private lateinit var id:String
    companion object{
        const val TAG="WEBVIEWACTIVITY"
        const val RAPOR_ID="RAPOR_ID"
    }


    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        GlobalFunctions.hideNavigationBar(this)

        // Hide Navigation and status bar
        id=intent.getStringExtra(RAPOR_ID)!!

        if (id.isNotEmpty()){
            checkFileAlreadyExist(id)
        }

    }

    private fun checkFileAlreadyExist(id: String) {
        val pdfLink="https://smartpilates.net/uploads/tanita/${id}.pdf"
        val fullUrl = "https://docs.google.com/gview?embedded=true&url=$pdfLink"

        openViewer(id,fullUrl)

        // TODO daha sonra indirme ve gösterme işlemleri yapılacak PDF İçin
       /* if (FileManager.checkFileIsAlreadyExists(id)){
            FileManager.openPDfFile(this,id)
        }
        else{

            openViewer(id,fullUrl)
            DownloadFile().execute(fullUrl,"$id.pdf")


        }*/

    }

    private fun openViewer(id: String,fullUrl:String) {

        Log.i(TAG,fullUrl)
        webviewRapor.settings.javaScriptEnabled = true
        webviewRapor.loadUrl(fullUrl)
    }


}
