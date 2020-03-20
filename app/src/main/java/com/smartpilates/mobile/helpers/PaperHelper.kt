package com.smartpilates.mobile.helpers

import android.content.Context
import io.paperdb.Paper

class PaperHelper(context: Context) {
    init {
        Paper.init(context)
    }
    companion object{
        private const val NOTIFY_LIST_KEY="NOTIFY_LIST"
    }

    fun adStoreNotify(notify:String){
        val notifyList=getStoreNotifyList()
        notifyList.add(notify)
        Paper.book().write(NOTIFY_LIST_KEY,notifyList)
    }

    private fun getStoreNotifyList(): ArrayList<String> {
        return Paper.book().read(NOTIFY_LIST_KEY, ArrayList())
    }

    fun checkNotifyDataExists(key: String): Boolean {
        val notifyList=getStoreNotifyList()
        notifyList.forEach {
            if (it==key)
                return true
        }
        return false
    }


    fun deleteStoredNotify(key:String){
        val notifyList=getStoreNotifyList()
        notifyList.remove(key)
        Paper.book().write(NOTIFY_LIST_KEY,notifyList)
    }
    fun deleteStoredNotifyList(){
        Paper.book().delete(NOTIFY_LIST_KEY)
    }
}