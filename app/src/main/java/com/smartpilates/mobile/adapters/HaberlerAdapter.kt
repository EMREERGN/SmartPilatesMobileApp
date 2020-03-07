package com.smartpilates.mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.HaberlerModel


class HaberlerViewHolder(view:View):RecyclerView.ViewHolder(view){

    val context=view.context

    val image:ImageView=view.findViewById(R.id.imageHaberler)
    val title:TextView=view.findViewById(R.id.titleHaberler)
    val createdAt:TextView=view.findViewById(R.id.createdAtHaberler)


}


class HaberlerAdapter(private val haberlerList:ArrayList<HaberlerModel>):RecyclerView.Adapter<HaberlerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HaberlerViewHolder {
        val root =LayoutInflater.from(parent.context).inflate(R.layout.bilgi_bankasi_card_item,parent,false)
        return HaberlerViewHolder(root)
    }

    override fun getItemCount(): Int {
       return haberlerList.size
    }

    override fun onBindViewHolder(holder: HaberlerViewHolder, position: Int) {
        val haber=haberlerList[position]


        // Resim
        Glide.with(holder.context)
            .load(haber.image)
            .apply(RequestOptions().placeholder(R.drawable.logo_big))
            .into(holder.image)

        holder.title.text=haber.title
        holder.createdAt.text=haber.createdAt

    }
}