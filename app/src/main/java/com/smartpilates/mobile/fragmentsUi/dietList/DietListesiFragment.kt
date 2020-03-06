package com.smartpilates.mobile.fragmentsUi.dietList


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.DietListAdapter
import com.smartpilates.mobile.adapters.DietListViewHolder
import com.smartpilates.mobile.fragmentsUi.lessons.LessonViewModel
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.DietListModel

/**
 * A simple [Fragment] subclass.
 */
class DietListesiFragment : Fragment() {

    private lateinit var dietViewModel: DietListesiViewModel
    private lateinit var dietListAdapter:DietListAdapter
    private lateinit var recyclerView:RecyclerView
    private lateinit var sharedPref:SharedPrfHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_diet_listesi, container, false)

        sharedPref= SharedPrfHelper(root.context)
        recyclerView=root.findViewById(R.id.recyclerviewDietList)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(root.context)
        }



        dietViewModel =
            ViewModelProviders.of(this).get(DietListesiViewModel::class.java)
        observeViewModel(dietViewModel)



        return root
    }

    private fun observeViewModel(viewModel: DietListesiViewModel) {

        viewModel.dietListObservable.observe(this,
            Observer<ArrayList<DietListModel>> {
                // En başa anamnez raporu eklenir
                it.add(0, DietListModel("",DietListViewHolder.ANAMNEZ_RAPORU_STRING,sharedPref.getUserID(),""))
                if (it!=null){
                    Log.i("Lessonfragment",it.toString())

                    dietListAdapter= DietListAdapter(it)
                    recyclerView.adapter=dietListAdapter
                    recyclerView.adapter!!.notifyDataSetChanged()


                }
            })
    }


}
