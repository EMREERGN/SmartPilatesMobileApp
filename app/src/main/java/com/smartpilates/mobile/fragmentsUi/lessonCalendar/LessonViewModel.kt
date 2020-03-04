package com.smartpilates.mobile.fragmentsUi.lessonCalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LessonViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Lessons Fragment"
    }
    val text: LiveData<String> = _text
}