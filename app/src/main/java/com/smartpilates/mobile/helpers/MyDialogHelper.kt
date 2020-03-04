package com.smartpilates.mobile.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.smartpilates.mobile.R
import java.io.ByteArrayOutputStream
import java.io.IOException

class MyDialogHelper(private val activity: Activity) {

    private fun initDialog(aboutDialog: Dialog) {
        aboutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        aboutDialog.setContentView(R.layout.about_layout)
        aboutDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showPrivacyPolicyView(){
        val aboutDialog= Dialog(activity)
        initDialog(aboutDialog)


        val dialogOpenSource = Dialog(aboutDialog.context)
        dialogOpenSource.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogOpenSource.setContentView(R.layout.privacy_policy)
        // OK BUTONUNA DOKUNULMAYI DİNLE
        dialogOpenSource.findViewById<Button>(R.id.ok_button).setOnClickListener{
            dialogOpenSource.cancel()
        }

        MyTask(activity,dialogOpenSource).execute()
    }

    @SuppressLint("StaticFieldLeak")
    private inner class MyTask(var activity: Activity, var dialogOpenSource: Dialog):
        AsyncTask<Any, Any, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            if (!activity.isFinishing) {
                dialogOpenSource.show() }
        }
        override fun doInBackground(vararg params: Any): String {
            return readOpensourceLicense()
        }
        override fun onPostExecute(aVoid: String) {
            super.onPostExecute(aVoid)
            if (!activity.isFinishing) {
                dialogOpenSource.findViewById<TextView>(R.id.license_textview).setText(aVoid)//burada, readOpensourceLicense metodu lisans txt'i okuyor.
                dialogOpenSource.findViewById<View>(R.id.license_scroolview).visibility = View.VISIBLE
                dialogOpenSource.findViewById<LinearLayout>(R.id.loading_layout).visibility = View.GONE
            }
        }

    }
    private fun readOpensourceLicense(): String {
        val inputStream = activity.resources.openRawResource(R.raw.kvkk)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var i: Int
        try {
            i = inputStream.read()
            while (i != -1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteArrayOutputStream.toString()
    }



    fun areYouSureQuit(){
        AlertDialog.Builder(activity)
            .setTitle("Çıkış Yapmak Üzeresiniz")
            .setMessage("Emin Misiniz ?")
            .setPositiveButton("EVET")
            { dialog, which ->

                activity.finish()
                dialog!!.dismiss()// kapat

            }
            .setNegativeButton("HAYIR"){
                    dialog, which ->
                dialog.dismiss()
            }
            .setNegativeButtonIcon(ContextCompat.getDrawable(activity,R.drawable.ic_close_red))
            .setPositiveButtonIcon(ContextCompat.getDrawable(activity,R.drawable.ic_done_green))
            .show()
    }







}