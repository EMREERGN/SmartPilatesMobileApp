package com.smartpilates.mobile.backgroundTask

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.smartpilates.mobile.helpers.NotificationManager
import com.smartpilates.mobile.model.NotifResponseModel
import com.smartpilates.mobile.repository.DataRepository
import java.text.SimpleDateFormat
import java.util.*


interface NotificationListener{
    fun onCompleteFetchData(notifList:ArrayList<NotifResponseModel>)
}

class NotificationWorker(val context: Context,workerParameters: WorkerParameters)
    :Worker(context,workerParameters){

    companion object{
        private const val TAG="MY_Worker"
        const val UNIQ_WORK_NAME="Notif Worker"
    }


    @SuppressLint("WrongThread")
    override fun doWork(): Result {

        DataRepository.getNotificationsWithListener(object :NotificationListener{

            override fun onCompleteFetchData(notifList: ArrayList<NotifResponseModel>) {
                var notifId=200
                notifList.forEach {


                    val pattern = "d MMMM yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(pattern, Locale("tr", "TR"))
                    val currentDate = simpleDateFormat.format(Date())

                    val dateNotif=it.cleandate.split(",")[0] // virgülün solu tarih kısmı
                    val timeNotif=it.cleandate.split(",")[1].trim() // virgülün solu tarih kısmı

                    // Eğer Bildirim aynı gün içinde ise
                    if (dateNotif == currentDate){
                        NotificationManager.
                        showNotification(context,notifId,it.description,"${it.title} - ${it.cleandate}")
                        notifId++
                    }

                }

            }

        })

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }


}