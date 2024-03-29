package com.smartpilates.mobile.fragmentsUi.measurement

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.smartpilates.mobile.R
import com.smartpilates.mobile.WebViewActivity
import com.smartpilates.mobile.model.DietInfoModel


class DietViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val context=view.context

    val tarih=view.findViewById<TextView>(R.id.tarihTxtDiet)
    val boy=view.findViewById<TextView>(R.id.boyTxtDiet)
    val kilo=view.findViewById<TextView>(R.id.kiloTxtDiet)
    val yag=view.findViewById<TextView>(R.id.yagTxtDiet)
    val kas=view.findViewById<TextView>(R.id.kasTxtDiet)
    val bel=view.findViewById<TextView>(R.id.belTxtDiet)
    val gogus=view.findViewById<TextView>(R.id.gogusTxtDiet)
    val kalca=view.findViewById<TextView>(R.id.kalcaTxtDiet)
    val karin=view.findViewById<TextView>(R.id.karinTxtDiet)
    val basen=view.findViewById<TextView>(R.id.basenTxtDiet)
    val omuz=view.findViewById<TextView>(R.id.omuzTxtDiet)
    val sagBacak=view.findViewById<TextView>(R.id.sagBacakTxtDiet)
    val solBacak=view.findViewById<TextView>(R.id.solBacakTxtDiet)
    val sagKol=view.findViewById<TextView>(R.id.sagKolTxtDiet)
    val solKol=view.findViewById<TextView>(R.id.solKolTxtDiet)
    val note=view.findViewById<TextView>(R.id.noteTxtDiet)
    val raporButton=view.findViewById<MaterialButton>(R.id.raporBtnDiet)

}


class DietAdapter(private val dietInfoList: ArrayList<DietInfoModel>) :
    RecyclerView.Adapter<DietViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.diet_card_item, parent, false)

        return DietViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return dietInfoList.size
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val diet = dietInfoList[position]

        holder.tarih.text=diet.created_at
        holder.boy.text=diet.boy
        holder.kilo.text=diet.kilo
        holder.yag.text=diet.yag_oran
        holder.kas.text=diet.kas_oran
        holder.bel.text=diet.bel
        holder.gogus.text=diet.gogus
        holder.kalca.text=diet.kalca
        holder.karin.text=diet.karin
        holder.basen.text=diet.basen
        holder.omuz.text=diet.omuz
        holder.sagBacak.text=diet.sag_bacak
        holder.solBacak.text=diet.sol_bacak
        holder.sagKol.text=diet.sag_kol
        holder.solKol.text=diet.sol_kol
        holder.note.text=diet.detail



        // Rapor görüntüleme

        val pdfLink="https://smartpilates.net/uploads/tanita/${diet.id}.pdf"
        val fullUrl = "https://docs.google.com/gview?embedded=true&url=$pdfLink"

        holder.raporButton.setOnClickListener {
            val intent=Intent(holder.context,WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.FULL_URL,fullUrl)
            intent.putExtra(WebViewActivity.PDF_ID,diet.id)
            holder.context.startActivity(intent)
        }







    }


}