package com.smartpilates.mobile.fragmentsUi.bilgiBankasiDetay


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smartpilates.mobile.R

/**
 * A simple [Fragment] subclass.
 */
class BilgiBankasiDetayFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var toolbar:Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val haberID=arguments?.getString("haberID")
        val root = inflater.inflate(R.layout.fragment_bilgi_bankasi_detay, container, false)

        val txt=root.findViewById<TextView>(R.id.haberDetayTextId)
        txt.text=haberID

        return root
    }


}
