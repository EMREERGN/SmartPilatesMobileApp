package com.smartpilates.mobile.fragmentsUi.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.NotifResponseModel
import org.w3c.dom.Text


class NotifViewHolder(view:View):RecyclerView.ViewHolder(view){
    val context=view.context
    val title=view.findViewById<TextView>(R.id.notifTitleTxt)
    val content=view.findViewById<TextView>(R.id.notifContentTxt)
    val date=view.findViewById<TextView>(R.id.notifDateTxt)

}


class NotifAdapter(private val notifList:ArrayList<NotifResponseModel>):RecyclerView.Adapter<NotifViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifViewHolder {
        val root =LayoutInflater.from(parent.context).inflate(R.layout.notif_card_item,parent,false)

        return NotifViewHolder(root)
    }

    override fun getItemCount(): Int {
       return notifList.size
    }

    override fun onBindViewHolder(holder: NotifViewHolder, position: Int) {

        val notif=notifList[position]

        holder.title.text=notif.title
        holder.content.text=notif.description
        holder.date.text=notif.cleandate


    }
}