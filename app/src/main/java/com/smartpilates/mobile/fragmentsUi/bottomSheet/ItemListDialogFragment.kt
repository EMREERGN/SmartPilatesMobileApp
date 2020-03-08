package com.smartpilates.mobile.fragmentsUi.bottomSheet

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R

const val ARG_ITEM_COUNT = "item_count"

class ItemListDialogFragment(private val categoryList:MutableList<String>,private val listener:CategoryClickListener) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bottomAdapter=BottomSheetAdapter(categoryList,listener)

        val recyclerView=view.findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(view.context)
            adapter=bottomAdapter
            adapter?.notifyDataSetChanged()
        }



    }

    companion object {


    }
}

interface CategoryClickListener{
    fun clickedCategory(category:String)
}

private class BottomSheetViewHolder(view:View):RecyclerView.ViewHolder(view){

    val context=view.context
    val contentText=view.findViewById<TextView>(R.id.bottomCardItemTxtId)

}


private class BottomSheetAdapter(private val categoryList:MutableList<String>,private val listener:CategoryClickListener) :RecyclerView.Adapter<BottomSheetViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {

        val root =LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_card_item,parent,false)

        return BottomSheetViewHolder(root)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {

        val category=categoryList[position]
        holder.contentText.text=category


        holder.contentText.setOnClickListener {
            listener.clickedCategory(category)
        }

    }
}
