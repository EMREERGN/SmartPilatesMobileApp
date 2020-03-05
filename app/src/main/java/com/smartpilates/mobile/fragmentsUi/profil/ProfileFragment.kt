package com.smartpilates.mobile.fragmentsUi.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartpilates.mobile.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var name:AppCompatTextView
    private lateinit var surName:AppCompatTextView
    private lateinit var email:AppCompatTextView
    private lateinit var phoneNumber:AppCompatTextView
    private lateinit var birthDate:AppCompatTextView
    private lateinit var adress:AppCompatTextView
    private lateinit var notes:AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(root)

        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        observeViewModel(profileViewModel)

        return root
    }

    private fun initViews(root: View) {
        name=root.findViewById(R.id.nameProfileTxt)
        surName=root.findViewById(R.id.surnameProfileTxt)
        email=root.findViewById(R.id.mailProfileTxt)
        phoneNumber=root.findViewById(R.id.phoneNumberProfileTxt)
        birthDate=root.findViewById(R.id.bornDateProfileTxt)
        adress=root.findViewById(R.id.adressProfileTxt)
        notes=root.findViewById(R.id.notesProfileTxt)
    }

    private fun observeViewModel(viewModel: ProfileViewModel) {
        viewModel.profileDataObservable.observe(this, Observer {
            if (it!=null){
                name.text=it.name
                surName.text=it.surname
                email.text=it.email
                phoneNumber.text=it.phone
                birthDate.text=it.birthDay
                adress.text=it.address
            }
        })

    }
}