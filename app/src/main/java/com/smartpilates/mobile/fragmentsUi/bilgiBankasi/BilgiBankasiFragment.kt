package com.smartpilates.mobile.fragmentsUi.bilgiBankasi


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.HaberlerAdapter
import com.smartpilates.mobile.fragmentsUi.bottomSheet.CategoryClickListener
import com.smartpilates.mobile.fragmentsUi.bottomSheet.ItemListDialogFragment
import com.smartpilates.mobile.model.HaberlerModel


/**
 * A simple [Fragment] subclass.
 */
class BilgiBankasiFragment : Fragment() {

    companion object{
        const val EGZERSIZ="Egzersiz"
        const val SAGLIK="Sağlık"
        const val BESLENME="Beslenme"
        var CURRENT_LIST= EGZERSIZ
    }

    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var bilgiBankasiViewModel:BilgiBankasiViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var haberlerAdapter:HaberlerAdapter
    private lateinit var tags:MutableList<String>
    private lateinit var root:View
    private lateinit var bottomSheet:ItemListDialogFragment

    var haberlerList=ArrayList<HaberlerModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_bilgi_bankasi, container, false)

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


        return root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main,menu)
        menu.findItem(R.id.action_filter_bilgi_bank).isVisible = true

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_filter_bilgi_bank->{

                bottomSheet=ItemListDialogFragment(tags,object :CategoryClickListener{
                    override fun clickedCategory(category: String) {
                        // Seçilen Kategorileri Listele
                        filterWithTags(category)
                        bottomSheet.dismiss()
                    }
                })
                bottomSheet.show(fragmentManager!!,"TAG")


            }
        }
        return true
    }


    private fun observeViewModel(viewModel: BilgiBankasiViewModel) {
        viewModel.dietListObservable.observe(this, Observer {
            if (it!=null){
                haberlerList=it // Bütün Liste
                filterList(EGZERSIZ)  // İlk Açılışta sadece Egzersiz Görünür
            }
        })
    }

    private fun initTabClickListener() {
        // Çift Tıklanma Olayı
        bottomNavigationView.setOnNavigationItemReselectedListener {
            bottomSheet=ItemListDialogFragment(tags,object :CategoryClickListener{
                override fun clickedCategory(category: String) {
                    // Seçilen Kategorileri Listele
                    filterWithTags(category)
                    bottomSheet.dismiss()
                }
            })
            bottomSheet.show(fragmentManager!!,"TAG")
            true
        }


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                // Üzerinde Bildirim Numarsı Göstermek için

                R.id.bottom_nav_egzersiz->{
                    filterList(EGZERSIZ)
                }
                R.id.bottom_nav_saglik->{
                    filterList(SAGLIK)
                }
                R.id.bottom_nav_beslenme->{
                    filterList(BESLENME)
                }
                else->{

                }
            }
            true
        }
    }

    private fun filterList(tag: String) {
        CURRENT_LIST= tag


        // Seçilen navigation bar'daki kategori haberleri görünür
        val filteredList=ArrayList<HaberlerModel>()
        haberlerList.forEach {
            if (it.tags==tag){
                filteredList.add(it)
            }
        }
        haberlerAdapter= HaberlerAdapter(filteredList)
        recyclerView.adapter=haberlerAdapter
        recyclerView.adapter!!.notifyDataSetChanged()

        // SetCount on navbar
        setOnlySelectedNavNumber(filteredList)

        setSortableTags(filteredList)
    }

    private fun setSortableTags(filteredList: ArrayList<HaberlerModel>) {
        // Filtrelemek için Tagler çekilir
        val filteredCategory=filteredList.groupBy { haber ->
            haber.tagsCat
        }.keys
        tags=filteredCategory.toMutableList()
        tags.add(0,getString(R.string.all_category))
        Log.i("BilgiBankasi",tags.toString())

    }

    private fun filterWithTags(category: String) {
        if (category==getString(R.string.all_category)){
            filterList(CURRENT_LIST)
        }
        else{
            val filteredList=ArrayList<HaberlerModel>()
            haberlerList.forEach {
                if (it.tagsCat==category){
                    filteredList.add(it)
                }
            }
            haberlerAdapter= HaberlerAdapter(filteredList)
            recyclerView.adapter=haberlerAdapter
            recyclerView.adapter!!.notifyDataSetChanged()


            setOnlySelectedNavNumber(filteredList)




        }

    }

    private fun setOnlySelectedNavNumber(filteredList: ArrayList<HaberlerModel>) {
        if (CURRENT_LIST== EGZERSIZ){
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_egzersiz).apply {
                isVisible=true
                number=filteredList.size
            }


            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_saglik).apply {
                isVisible=false
            }
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_beslenme).apply {
                isVisible=false
            }


        }else if (CURRENT_LIST== SAGLIK){
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_saglik).apply {
                isVisible=true
                number=filteredList.size
            }


            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_beslenme).apply {
                isVisible=false
            }
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_egzersiz).apply {
                isVisible=false
            }


        }else if(CURRENT_LIST== BESLENME){
            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_beslenme).apply {
                isVisible=true
                number=filteredList.size
            }


            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_saglik).apply {
                isVisible=false
            }

            bottomNavigationView.getOrCreateBadge(R.id.bottom_nav_egzersiz).apply {
                isVisible=false
            }
        }

    }


}
