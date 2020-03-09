package com.smartpilates.mobile.fragmentsUi.bilgiBankasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.HaberlerModel


interface HaberClickListener{
    fun onclicked(haberID:String)
}


class BilgiBankasiViewHolder(view:View):RecyclerView.ViewHolder(view){

    val context=view.context

    val container:CardView=view.findViewById(R.id.bilgiBankContainer)
    val image:ImageView=view.findViewById(R.id.imageHaberler)
    val title:TextView=view.findViewById(R.id.titleHaberler)
    val createdAt:TextView=view.findViewById(R.id.createdAtHaberler)

}


class BilgiBankasiAdapter(private val haberlerList:ArrayList<HaberlerModel>,private val listener:HaberClickListener):RecyclerView.Adapter<BilgiBankasiViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BilgiBankasiViewHolder {
        val root =LayoutInflater.from(parent.context).inflate(R.layout.bilgi_bankasi_card_item,parent,false)

        return BilgiBankasiViewHolder(root)
    }

    override fun getItemCount(): Int {
       return haberlerList.size
    }

    override fun onBindViewHolder(holder: BilgiBankasiViewHolder, position: Int) {
        val haber=haberlerList[position]


        // Resim
        Glide.with(holder.context)
            .load(haber.image)
            .apply(RequestOptions().placeholder(R.drawable.logo_big))
            .into(holder.image)

        holder.title.text=haber.title
        holder.createdAt.text=haber.createdAt



        // Click Listener
        holder.container.setOnClickListener {
            listener.onclicked(haber.id)

        }

    }
}