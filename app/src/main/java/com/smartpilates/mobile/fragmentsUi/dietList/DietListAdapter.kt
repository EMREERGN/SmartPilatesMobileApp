package com.smartpilates.mobile.fragmentsUi.dietList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.smartpilates.mobile.R
import com.smartpilates.mobile.WebViewActivity
import com.smartpilates.mobile.fragmentsUi.dietList.DietListViewHolder.Companion.ANAMNEZ_RAPORU_STRING
import com.smartpilates.mobile.model.DietListModel


class DietListViewHolder(view:View):RecyclerView.ViewHolder(view){

    companion object{
        const val ANAMNEZ_RAPORU_STRING="Anamnez Raporu"
    }

    val context=view.context

    val title:TextView=view.findViewById(R.id.dietListesiTitleTxt)
    val content:TextView=view.findViewById(R.id.dietListesiContentTxt)
    val button: MaterialButton =view.findViewById(R.id.dietListesiButton)


}

class DietListAdapter(private val dietList:ArrayList<DietListModel>):RecyclerView.Adapter<DietListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietListViewHolder {
        val root =LayoutInflater.from(parent.context).inflate(R.layout.diet_list_card_item,parent,false)

        return DietListViewHolder(
            root
        )
    }

    override fun getItemCount(): Int {
       return dietList.size
    }

    override fun onBindViewHolder(holder: DietListViewHolder, position: Int) {
        val diet=dietList[position]

        holder.title.text=diet.date_added
        holder.content.text="Diyetisyenimiz Tarafından ${diet.date_added} tarihinde sizin için hazırlanan diyet listesini görüntüleyebilirsiniz"
        holder.button.icon=ContextCompat.getDrawable(holder.context,R.drawable.ic_calendar)

        // Eğer Anamnez Raporu ise
        if (diet.date_added==ANAMNEZ_RAPORU_STRING){
            holder.content.text=holder.context.getString(R.string.anamnez_content)
            holder.button.setOnClickListener {

                val pdfLink="https://smartpilates.net/uploads/analiz/${diet.customer_id}.pdf"
                val fullUrl = "https://docs.google.com/gview?embedded=true&url=$pdfLink"

                val intent= Intent(holder.context, WebViewActivity::class.java)
                intent.putExtra(WebViewActivity.FULL_URL,fullUrl)
                holder.context.startActivity(intent)

            }
        }
        // Diğer Raporlar ise
        else{

            holder.button.setOnClickListener {
                val pdfLink="https://smartpilates.net/uploads/diyetpdf/${diet.id}.pdf"
                val fullUrl = "https://docs.google.com/gview?embedded=true&url=$pdfLink"

                val intent= Intent(holder.context, WebViewActivity::class.java)
                intent.putExtra(WebViewActivity.FULL_URL,fullUrl)
                holder.context.startActivity(intent)
            }
        }



    }
}