package com.smartpilates.mobile


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.smartpilates.mobile.backgroundTask.NotificationWorker
import com.smartpilates.mobile.fragmentsUi.home.HomeFragment
import com.smartpilates.mobile.helpers.MyDialogHelper
import com.smartpilates.mobile.helpers.NotificationManager
import com.smartpilates.mobile.listeners.OnBackPressed
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(),OnBackPressed {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val myDialogHelper=MyDialogHelper(this)
    lateinit var navController:NavController


    override fun onBackPressed() {
        //myDialogHelper.areYouSureQuit()
        //navController.navigate(R.id.action_nav_home_self)
        tellFragments()
        super.onBackPressed();

    }
    private fun tellFragments() {
        val fragments: List<Fragment> =
            supportFragmentManager.fragments
        for (f in fragments) {
            if (f is HomeFragment)
                f.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)


        fab.setOnClickListener { view ->

        }


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_member_data,
                R.id.nav_lesson_calendar, R.id.nav_measurement_information, R.id.nav_diyet_list,
                R.id.nav_bilgi_bankasi,R.id.nav_uzmana_sor,R.id.nav_notifications
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        setWorkManager()


    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.action_quit_are_you_sure).isVisible = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_quit_are_you_sure->{
                quitUser()
            }
        }


        return true
    }
    private fun quitUser(){
        myDialogHelper.areYouSureQuit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun setWorkManager(){

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()

        val notifWorkerRequest=
            PeriodicWorkRequestBuilder<NotificationWorker>(1,TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                NotificationWorker.UNIQ_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP, // eski işi devam ettir
                notifWorkerRequest)

    }

}
