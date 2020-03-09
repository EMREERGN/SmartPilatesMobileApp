package com.smartpilates.mobile.fragmentsUi.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.smartpilates.mobile.R
import com.smartpilates.mobile.fragmentsUi.bottomSheet.CategoryClickListener
import com.smartpilates.mobile.fragmentsUi.bottomSheet.ItemListDialogFragment
import com.smartpilates.mobile.helpers.MyDialogHelper
import com.smartpilates.mobile.helpers.SharedPrfHelper
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var navController:NavController
    lateinit var sharedPref:SharedPrfHelper
    lateinit var root:View




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=findNavController(view)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.userName.observe(this, Observer {
            textViewWelcome.text = it
        })


        sharedPref= SharedPrfHelper(root.context)

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