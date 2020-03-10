package com.smartpilates.mobile.fragmentsUi.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.LessonsGetDataModel


class LessonViewHolder(view: View):RecyclerView.ViewHolder(view){
    val lessonTitle:AppCompatTextView=view.findViewById(R.id.lessonTitle)
    val lessonDate:AppCompatTextView=view.findViewById(R.id.lessonDate)
    val lessonTime:AppCompatTextView=view.findViewById(R.id.lessonTime)
    val lessonName:AppCompatTextView=view.findViewById(R.id.lessonName)
    val container:LinearLayout=view.findViewById(R.id.futureLessonsCardContainer)

    companion object {
        const val LESSON_PLAYER_ID_KEY="LESSON_PLAYER_ID"
    }

}



class LessonsAdapater(private val lessonList:ArrayList<LessonsGetDataModel>):RecyclerView.Adapter<LessonViewHolder>() {

    private val tag="LessonAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_card_item, parent, false)
        return LessonViewHolder(view)
    }


    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {

        val currentLesson=lessonList[position]

        holder.lessonTitle.text=currentLesson.name
        holder.lessonDate.text= currentLesson.lesson_date
        holder.lessonTime.text= currentLesson.lesson_time
        holder.lessonName.text= currentLesson.title

        // tamamlanma durumuna göre renk değiştir

        // GEÇMİŞ DERSLER
        if(lessonList[position].completed=="Y"){
            holder.container.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.gradiant_blue)
        }
        // GELECEK DERSLER
        else{

        }


    }


}
