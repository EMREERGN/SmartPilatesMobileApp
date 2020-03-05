package com.smartpilates.mobile.fragmentsUi.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.smartpilates.mobile.R



class HomeFragment : Fragment() {

    lateinit var navController:NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=findNavController(view)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

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
            navController.navigate(R.id.action_nav_home_to_nav_lesson_calendar)
        }

        memberDatasContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_member_data)
        }

        measurementInfoContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_measurement_information)
        }

        dietListContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_diyet_list)
        }

        bilgiBankContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_bilgi_bankasi)
        }

        askUzmanContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_uzmana_sor)
        }

        profilContainer.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_nav_profile)
        }


    }
}