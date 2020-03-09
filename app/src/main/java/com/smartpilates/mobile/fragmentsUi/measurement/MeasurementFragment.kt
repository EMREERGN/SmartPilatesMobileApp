package com.smartpilates.mobile.fragmentsUi.measurement


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.DietAdapter


class MeasurementFragment : Fragment() {

    private lateinit var recyclerViewDiet:RecyclerView

    private lateinit var measurementViewModel:MeasurementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_measurement, container, false)


        measurementViewModel= ViewModelProvider(this).get(MeasurementViewModel::class.java)

        recyclerViewDiet=view.findViewById(R.id.recyclerViewDiet)
        recyclerViewDiet.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(view.context)
        }

        observeViewModel(measurementViewModel)


        return view


    }

    private fun observeViewModel(viewModel: MeasurementViewModel) {
        viewModel.dietInfoListObservable.observe(this, Observer {
            if (it!=null){
                val dietAdapter=DietAdapter(it)
                recyclerViewDiet.adapter=dietAdapter
                recyclerViewDiet.adapter!!.notifyDataSetChanged()
            }
        })
    }


}
