package com.smartpilates.mobile.helpers

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.smartpilates.mobile.helpers.FileManager.Companion.PDF_FILE_FOLDER_NAME
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class FileManager{

    companion object{
        const val PDF_FILE_FOLDER_NAME="SmartPlatesPDFFolder"

        fun openPDfFile(context: Context,pdfName:String){
            val pdfFile = File(Environment.getExternalStorageDirectory().toString() + "/$PDF_FILE_FOLDER_NAME/$pdfName.pdf") // -> filename = 355.pdf
            val path: Uri = Uri.fromFile(pdfFile)
            val pdfIntent = Intent(Intent.ACTION_VIEW)
            pdfIntent.setDataAndType(path, "application/pdf")
            pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            try {
                context.startActivity(pdfIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No Application available to view PDF", Toast.LENGTH_SHORT).show()
            }
        }

        fun checkFileIsAlreadyExists(pdfName:String): Boolean {
            val pdfFile = File(Environment.getExternalStorageDirectory().toString() + "/$PDF_FILE_FOLDER_NAME/$pdfName.pdf") // -> filename = 355.pdf
            return pdfFile.exists()
        }
    }


}

class DownloadFile : AsyncTask<String,Void,Void>(){
    override fun doInBackground(vararg strings: String?): Void? {

        val fileUrl = strings[0]!! // -> http://maven.apache.org/maven-1.x/maven.pdf
        val fileName = strings[1]!! // -> maven.pdf

        val extStorageDirectory: String = Environment.getExternalStorageDirectory().toString()
        val folder = File(extStorageDirectory, PDF_FILE_FOLDER_NAME)
        folder.mkdir()
        val pdfFile = File(folder, fileName)
        try {
            pdfFile.createNewFile()
            Log.i(FileDownloader.TAG,"Pdf Created")
        } catch (e: IOException) {
            Log.i(FileDownloader.TAG,"Pdf Not Created : $e")
            e.printStackTrace()
        }
        FileDownloader.downloadFile(fileUrl, pdfFile)
        return null
    }

}


class FileDownloader {
    companion object{
        const val MEGABYTE = 1024 * 1024
        const val TAG="FILEDOWNLOADER"

        fun downloadFile(fileUrl: String, directory: File) {
            try {
                val url = URL(fileUrl)
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                /*urlConnection.requestMethod = "GET"
                urlConnection.setDoOutput(true)*/
                urlConnection.connect()


                val inputStream: InputStream = urlConnection.inputStream
                val fileOutputStream = FileOutputStream(directory)


                val totalSize: Int = urlConnection.contentLength


                val buffer = ByteArray(MEGABYTE)
                var bufferLength = 0
                while (inputStream.read(buffer).also { bufferLength = it } > 0) {
                    fileOutputStream.write(buffer, 0, bufferLength)
                }


                fileOutputStream.close()
            } catch (e: FileNotFoundException) {
                Log.i(TAG,e.toString())
                e.printStackTrace()
            } catch (e: MalformedURLException) {
                Log.i(TAG,e.toString())
                e.printStackTrace()
            } catch (e: IOException) {
                Log.i(TAG,e.toString())
                e.printStackTrace()
            }
        }
    }
}


