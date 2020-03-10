package com.smartpilates.mobile.fragmentsUi.notifications


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartpilates.mobile.R
import com.smartpilates.mobile.model.NotifResponseModel

/**
 * A simple [Fragment] subclass.
 */
class NotifFragment : Fragment() {

    lateinit var notifViewModel:NotifViewModel
    lateinit var root:View
    lateinit var recyclerView:RecyclerView
    lateinit var notifAdapter:NotifAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_bildirimler, container, false)

        recyclerView=root.findViewById(R.id.notifRecyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(root.context)
        }

        notifViewModel=ViewModelProvider(this).get(NotifViewModel::class.java)
        observableViewModel(notifViewModel)


        return root
    }

    private fun observableViewModel(viewModel: NotifViewModel) {
        viewModel.notifResponseObservable.observe(this, Observer {
            if (it!=null){
                updateRecylerView(it)
            }
        })
    }

    private fun updateRecylerView(notifList:ArrayList<NotifResponseModel>) {
        notifAdapter= NotifAdapter(notifList)
        recyclerView.apply {
            adapter=notifAdapter.apply {
                notifyDataSetChanged()
            }
        }
    }


}
