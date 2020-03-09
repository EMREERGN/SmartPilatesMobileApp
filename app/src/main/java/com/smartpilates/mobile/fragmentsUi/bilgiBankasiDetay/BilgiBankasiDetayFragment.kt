package com.smartpilates.mobile.fragmentsUi.bilgiBankasiDetay


import android.annotation.SuppressLint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textview.MaterialTextView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.helpers.SharedPrfHelper


/**
 * A simple [Fragment] subclass.
 */
class BilgiBankasiDetayFragment : Fragment() {

    lateinit var haberDetayViewModel:BilgiBankasiDetayViewModel
    lateinit var navController: NavController
    lateinit var webview:WebView
    private lateinit var sharedPref:SharedPrfHelper
    private lateinit var categoryTextDetay: MaterialTextView
    private lateinit var calendarTextDetay: MaterialTextView
    private lateinit var haberDetayImageView: ImageView
    lateinit var root:View


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_bilgi_bankasi_detay, container, false)


        webview=root.findViewById(R.id.haberDetayWebView)
        categoryTextDetay=root.findViewById(R.id.categoryTextDetay)
        calendarTextDetay=root.findViewById(R.id.calendarTextDetay)
        haberDetayImageView=root.findViewById(R.id.haberDetayImageView)


        val haberID=arguments?.getString("haberID") as String
        sharedPref= SharedPrfHelper(root.context).apply {
            setHaberId(haberID)
        }




        haberDetayViewModel =
                ViewModelProvider(this).get(BilgiBankasiDetayViewModel::class.java)
        observeViewModel(haberDetayViewModel)


        return root
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel(viewModel: BilgiBankasiDetayViewModel) {
        viewModel.haberDetayObservable.observe(this, Observer {
            val haberDetay=it[0]

            Glide.with(this)
                    .load(haberDetay.image)
                    .apply(RequestOptions().placeholder(R.drawable.logo_big))
                    .into(haberDetayImageView)
            categoryTextDetay.text="Kategori : ${haberDetay.tags} / ${haberDetay.tagsCat}"
            calendarTextDetay.text="Paylaşım Tarihi : ${haberDetay.createdAt}"

            // Webview
            val mimeType = "text/html"
            val encoding = "UTF-8"
            val html = haberDetay.description

            val userAgent = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
            webview.settings.apply {
                javaScriptEnabled=true
                userAgentString=userAgent
            }
            webview.loadDataWithBaseURL("", html, mimeType, encoding, "")
        })

    }


}


