package com.smartpilates.mobile.fragmentsUi.bilgiBankasi


import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.HaberlerAdapter
import com.smartpilates.mobile.model.HaberlerModel
import kotlinx.android.synthetic.main.app_bar_main.*


/**
 * A simple [Fragment] subclass.
 */
class BilgiBankasiFragment : Fragment() {

    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var bilgiBankasiViewModel:BilgiBankasiViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var haberlerAdapter:HaberlerAdapter

    var haberlerList=ArrayList<HaberlerModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_bilgi_bankasi, container, false)

        bottomNavigationView=root.findViewById(R.id.bottom_navigation_bilgi_bankasi)

        recyclerView=root.findViewById(R.id.recyclerHaberler)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(root.context)
        }

        setHasOptionsMenu(true)

        initTabClickListener()


        bilgiBankasiViewModel =
            ViewModelProviders.of(this).get(BilgiBankasiViewModel::class.java)
        observeViewModel(bilgiBankasiViewModel)

        // Üzerinde Bildirim Numarsı Göstermek için
       /* val badge = bottomNavigationView.getOrCreateBadge(menuItemId)
        badge.isVisible = true*/

        return root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main,menu)
        menu.findItem(R.id.action_filter_bilgi_bank).isVisible = true

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun observeViewModel(viewModel: BilgiBankasiViewModel) {
        viewModel.dietListObservable.observe(this, Observer {
            if (it!=null){
                haberlerList=it

                haberlerAdapter= HaberlerAdapter(it)
                recyclerView.adapter=haberlerAdapter
                recyclerView.adapter!!.notifyDataSetChanged()
            }
        })
    }

    private fun initTabClickListener() {



        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.bottom_nav_egzersiz->{
                    filterList("Egzersiz")
                }
                R.id.bottom_nav_saglik->{
                    filterList("Sağlık")
                }
                R.id.bottom_nav_beslenme->{
                    filterList("Beslenme")
                }
                else->{

                }
            }
            true
        }
    }

    private fun filterList(tag: String) {
        val filteredList=ArrayList<HaberlerModel>()
        haberlerList.forEach {
            if (it.tags==tag){
                filteredList.add(it)
            }
        }
        haberlerAdapter= HaberlerAdapter(filteredList)
        recyclerView.adapter=haberlerAdapter
        recyclerView.adapter!!.notifyDataSetChanged()

    }


}
