package com.smartpilates.mobile.fragmentsUi.lessons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.LessonsAdapater
import com.smartpilates.mobile.model.LessonsGetDataModel

class LessonFragment : Fragment() {

    private lateinit var lessonViewModel: LessonViewModel
    private lateinit var lessonsAdapater:LessonsAdapater
    private lateinit var recyclerViewLessons:RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("Lessonfragment","onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        lessonViewModel =
            ViewModelProviders.of(this).get(LessonViewModel::class.java)
        observeViewModel(lessonViewModel)

    }

    private fun observeViewModel(viewModel : LessonViewModel){
        Log.i("Lessonfragment","observeViewModel")
        // Update the list when the data changes
        // Update the list when the data changes
        viewModel.lessonsListObservable.observe(this,
            Observer<ArrayList<LessonsGetDataModel>> {
                if (it!=null){
                    Log.i("Lessonfragment",it.toString())
                    lessonsAdapater= LessonsAdapater(it)
                    recyclerViewLessons.adapter=lessonsAdapater
                    recyclerViewLessons.adapter!!.notifyDataSetChanged()

                }
            })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("Lessonfragment","onCreateView")
        lessonViewModel =
            ViewModelProviders.of(this).get(LessonViewModel::class.java)
        val root =inflater.inflate(R.layout.fragment_lesson_calendar, container, false)

        recyclerViewLessons=root.findViewById<RecyclerView>(R.id.recyclerViewLessons).apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(root.context)
        }

        return root
    }
}