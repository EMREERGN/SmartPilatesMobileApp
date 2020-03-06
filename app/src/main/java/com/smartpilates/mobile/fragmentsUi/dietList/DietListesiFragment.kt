package com.smartpilates.mobile.fragmentsUi.dietList


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smartpilates.mobile.R
import com.smartpilates.mobile.fragmentsUi.lessons.LessonViewModel
import com.smartpilates.mobile.model.DietListModel

/**
 * A simple [Fragment] subclass.
 */
class DietListesiFragment : Fragment() {

    private lateinit var dietViewModel: DietListesiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_diet_listesi, container, false)

        dietViewModel =
            ViewModelProviders.of(this).get(DietListesiViewModel::class.java)
        observeViewModel(dietViewModel)



        return root
    }

    private fun observeViewModel(viewModel: DietListesiViewModel) {

        viewModel.dietListObservable.observe(this,
            Observer<ArrayList<DietListModel>> {
                if (it!=null){
                    Log.i("Lessonfragment",it.toString())




                }
            })
    }


}
