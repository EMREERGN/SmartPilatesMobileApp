package com.smartpilates.mobile.fragmentsUi.lessonCalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartpilates.mobile.R

class LessonFragment : Fragment() {

    private lateinit var lessonViewModel: LessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lessonViewModel =
            ViewModelProviders.of(this).get(LessonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lesson_calendar, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        lessonViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}