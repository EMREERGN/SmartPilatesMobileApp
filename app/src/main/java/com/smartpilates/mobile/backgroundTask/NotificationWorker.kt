package com.smartpilates.mobile.backgroundTask

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.*
import com.smartpilates.mobile.helpers.NotificationManager
import com.smartpilates.mobile.helpers.PaperHelper
import com.smartpilates.mobile.model.NotifResponseModel
import com.smartpilates.mobile.repository.DataRepository
import io.paperdb.Paper
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


interface NotificationListener {
    fun onCompleteFetchData(notifList: ArrayList<NotifResponseModel>)
}

class NotificationWorker(val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    companion object {
        private const val TAG = "MY_Worker"
        private const val UNIQ_WORK_NAME = "Notif Worker"

        // Tek seferlik Worker Request
        fun setOneTimeRequestWorker(context: Context){
            val constraints = Constraints.Builder()
                .setRequiresCharging(false)
                .build()

            val oneTimeRequest=
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setConstraints(constraints)
                    .build()
            WorkManager
                .getInstance(context).enqueue(oneTimeRequest)
        }
        // Periodik zamamanlı Request
        fun setPeriodicRequest(context: Context){
            val constraints = Constraints.Builder()
                .setRequiresCharging(false)
                .build()

            // Periodic Request
            val notifWorkerRequest=
                PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .build()
            WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork(
                    NotificationWorker.UNIQ_WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP, // eski işi devam ettir
                    notifWorkerRequest)
        }
    }


    @SuppressLint("WrongThread")
    override fun doWork(): Result {

        val paperHelper=PaperHelper(context)

        DataRepository.getNotificationsWithListener(object : NotificationListener {

            override fun onCompleteFetchData(notifList: ArrayList<NotifResponseModel>) {
                var notifId = 200
                notifList.forEach {
                    val pattern = "d MMMM yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(pattern, Locale("tr", "TR"))
                    val currentDate = simpleDateFormat.format(Date())

                    val dateNotif = it.cleandate.split(",")[0] // virgülün solu tarih kısmı
                    val key=it.title+it.cleandate // gösterilen bildirim bu anahtar ile kaydedilir

                    // Eğer Bildirim aynı gün içinde ise
                    if (dateNotif == currentDate) {
                        // Eğer bildirim daha önceden gösterilmemiş ise
                        if (!paperHelper.checkNotifyDataExists(key)){
                            NotificationManager
                                .showNotification(context, notifId, it.description, "${it.title} - ${it.cleandate}") // Bildirim Gösterilir
                            paperHelper.adStoreNotify(key) // Gösterilen bildirimler kayıtlı tutulur
                            notifId++
                        }
                    }
                    // Eğer bildirim önceki tarihte ise kayıtlarda fazla yer tutmaması için geçerli key listeden silinir
                    else{
                        paperHelper.deleteStoredNotify(key)
                    }
                }
            }
        })

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }


}