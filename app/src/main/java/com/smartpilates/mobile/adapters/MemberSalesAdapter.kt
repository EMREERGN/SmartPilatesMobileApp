package com.smartpilates.mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.MemberSalesModel


class MemberSalesViewHolder(view:View):RecyclerView.ViewHolder(view){
    val startDateMemberSales=view.findViewById<AppCompatTextView>(R.id.startDateMemberSales)
    val endDateMemberSales=view.findViewById<AppCompatTextView>(R.id.endDateMemberSales)
    val totalLessonCountMemberSales=view.findViewById<AppCompatTextView>(R.id.totalLessonCountMemberSales)
    val completedLessonsMemberSales=view.findViewById<AppCompatTextView>(R.id.completedLessonsMemberSales)
    val totalPriceMemberSales=view.findViewById<AppCompatTextView>(R.id.totalPriceMemberSales)
}

class MemberSalesAdapter (private val memberSaleList: ArrayList<MemberSalesModel>):RecyclerView.Adapter<MemberSalesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberSalesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.member_sales_card_item,parent,false)
        return MemberSalesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memberSaleList.size
    }

    override fun onBindViewHolder(holder: MemberSalesViewHolder, position: Int) {
        val currentMemberSale =memberSaleList[position]

        holder.startDateMemberSales.text=currentMemberSale.startDate
        holder.endDateMemberSales.text=currentMemberSale.endDate
        holder.totalLessonCountMemberSales.text=currentMemberSale.orderHour
        holder.completedLessonsMemberSales.text=currentMemberSale.completed
        holder.totalPriceMemberSales.text=currentMemberSale.totalPrice
    }


}