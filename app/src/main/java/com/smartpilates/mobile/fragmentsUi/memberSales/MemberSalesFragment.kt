package com.smartpilates.mobile.fragmentsUi.memberSales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.adapters.MemberSalesAdapter
import com.smartpilates.mobile.model.MemberSalesModel
import kotlinx.android.synthetic.main.fragment_member_data.*

class MemberSalesFragment : Fragment() {

    private lateinit var memberSalesViewModel: MemberSalesViewModel
    private lateinit var memberSalesAdapter: MemberSalesAdapter
    private lateinit var recyclerViewMemberSales:RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_member_data, container, false)
        recyclerViewMemberSales=root.findViewById(R.id.recyclerViewMemberSales)


        // init viewModel
        memberSalesViewModel= ViewModelProvider(this).get(MemberSalesViewModel::class.java)



        recyclerViewMemberSales.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(root.context)
        }

        observeViewModel(memberSalesViewModel)


        return root
    }

    private fun observeViewModel(viewModel: MemberSalesViewModel) {
        viewModel.memberSalesObservable.observe(this,
            Observer {
                if (it!=null){
                    memberSalesAdapter= MemberSalesAdapter((it))
                    recyclerViewMemberSales.adapter=memberSalesAdapter
                    recyclerViewMemberSales.adapter!!.notifyDataSetChanged()
                }
            })

    }
}