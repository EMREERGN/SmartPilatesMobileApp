package com.smartpilates.mobile.fragmentsUi.notifications


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartpilates.mobile.R

/**
 * A simple [Fragment] subclass.
 */
class BildirimlerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bildirimler, container, false)
    }


}
