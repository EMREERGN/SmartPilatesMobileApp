package com.smartpilates.mobile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smartpilates.mobile.helpers.GlobalFunctions
import com.smartpilates.mobile.helpers.SharedPrfHelper

class SplashScreen : AppCompatActivity() {

    companion object{
        const val WRITE_EXTERNAL_PERMISSION_CODE=151
    }

    private val sharedPref=SharedPrfHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        GlobalFunctions.hideNavigationBar(this)
        checkPermissions()

    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),WRITE_EXTERNAL_PERMISSION_CODE)
        }
        else{
            checkUserForLogin()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == WRITE_EXTERNAL_PERMISSION_CODE) {

            permissions.forEachIndexed { i, permission ->
                if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                {
                    // user rejected the permission
                    val showRationale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        shouldShowRequestPermissionRationale( permission )
                    } else {
                        TODO("VERSION.SDK_INT < M")
                    }
                    if (!showRationale) {
                        finish()
                        // işaretlendi "never ask again"
                        sharedPref.setPermissionNeverShowAgain(true)

                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE == permission)
                    {
                        finish()
                        // dont ask again işaretlenmeden reddedildi
                        sharedPref.setPermissionNeverShowAgain(false)

                    }
                }
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    checkUserForLogin()
                }
            }

        }




    }

    private fun checkUserForLogin() {

        val sharedPrefHelper= SharedPrfHelper(this)
        val userId=sharedPrefHelper.getUserID()
        if (userId.isNotEmpty() && userId!=""){
            val intent= Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }
        else{
            val intent= Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }

    }

}
