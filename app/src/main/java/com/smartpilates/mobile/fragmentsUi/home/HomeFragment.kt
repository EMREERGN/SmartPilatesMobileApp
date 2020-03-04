package com.smartpilates.mobile.fragmentsUi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.smartpilates.mobile.R
import com.smartpilates.mobile.fragmentsUi.lessonCalendar.LessonFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        /*homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/

        buttonListener(root)
        return root






    }

    private fun buttonListener(root: View) {

        val lessonCalenndarContainer=root.findViewById<CardView>(R.id.lessonCalenndarContainer)
        val memberDatasContainer=root.findViewById<CardView>(R.id.dataMemberContainer)
        val measurementInfoContainer=root.findViewById<CardView>(R.id.measurementInfoContainer)
        val dietListContainer=root.findViewById<CardView>(R.id.dietListContainer)
        val bilgiBankContainer=root.findViewById<CardView>(R.id.bilgiBankContainer)
        val askUzmanContainer=root.findViewById<CardView>(R.id.askUzmanContainer)
        val profilContainer=root.findViewById<CardView>(R.id.profilContainer)

        lessonCalenndarContainer.setOnClickListener {


            val transaction = activity?.supportFragmentManager!!.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                replace(R.id.nav_host_fragment,LessonFragment() )
                addToBackStack(null)
            }.commit()

            Toast.makeText(root.context,"lessonCalenndarContainer",Toast.LENGTH_SHORT).show()

        }

    }
}