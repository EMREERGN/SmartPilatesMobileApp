package com.smartpilates.mobile.fragmentsUi.lessons

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.LessonsGetDataModel

class LessonFragment : Fragment() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var lessonViewModel: LessonViewModel
    private lateinit var lessonsAdapater: LessonsAdapater
    private lateinit var recyclerViewLessons:RecyclerView

    private var allLessonList=ArrayList<LessonsGetDataModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("Lessonfragment","onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        lessonViewModel =
                ViewModelProvider(this).get(LessonViewModel::class.java)
        observeViewModel(lessonViewModel)

    }

    private fun observeViewModel(viewModel : LessonViewModel){
        Log.i("Lessonfragment","observeViewModel")
        // Update the list when the data changes
        // Update the list when the data changes
        viewModel.lessonsListObservable.observe(this,
            Observer {
                if (it!=null){
                    allLessonList=it
                    updateList(true)
                }
            })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root =inflater.inflate(R.layout.fragment_lesson_calendar, container, false)

        Log.i("Lessonfragment","onCreateView")
        lessonViewModel =
                ViewModelProvider(this).get(LessonViewModel::class.java)


        bottomNavigationView=root.findViewById(R.id.bottom_navigation_lessons)

        recyclerViewLessons=root.findViewById<RecyclerView>(R.id.recyclerViewLessons).apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(root.context)
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_gelecek_dersler->{
                    updateList(true)
                }
                R.id.bottom_nav_gecmis_dersler->{
                    updateList(false)
                }
            }

            true
        }



        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.menu_lesson_bottom,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateList(gelecekDersler: Boolean) {
        val filteredList=ArrayList<LessonsGetDataModel>()
        // Gelecek Dersler
        if (gelecekDersler){
            allLessonList.forEach {
                if (it.completed=="N"){
                    filteredList.add(it)
                }
            }
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_gelecek_dersler).apply {
                isVisible=true
                number=filteredList.size
            }
        }
        // Geçmiş Dersler
        else{
            allLessonList.forEach {
                if (it.completed=="Y"){
                    filteredList.add(it)
                }
            }
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_gecmis_dersler).apply {
                isVisible=true
                number=filteredList.size
            }
        }

        lessonsAdapater=
            LessonsAdapater(
                filteredList
            )
        recyclerViewLessons.adapter=lessonsAdapater
        recyclerViewLessons.adapter!!.notifyDataSetChanged()
    }

}















