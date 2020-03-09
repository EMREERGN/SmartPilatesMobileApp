package com.smartpilates.mobile.fragmentsUi.uzmanaSor


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButton
import com.smartpilates.mobile.R
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.MessagePostModel
import com.smartpilates.mobile.model.MessageResponseModel
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass.
 */
class UzmanaSorFragment : Fragment() {

    companion object{
        const val TAG="UZMANA_SOR"
    }

    lateinit var root:View
    lateinit var sendButton:MaterialButton
    lateinit var subjectText:TextView
    lateinit var message:TextView
    lateinit var sharedPref:SharedPrfHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_uzmana_sor, container, false)
        sendButton=root.findViewById(R.id.sendUzmanaSorBtn)
        subjectText=root.findViewById(R.id.subjectUzmanaSorTxt)
        message=root.findViewById(R.id.messageUzmanaSorTxt)


        sharedPref= SharedPrfHelper(root.context)

        sendButton.setOnClickListener {

            val retrofit= RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postMessageModel=MessagePostModel(message.text.toString(),subjectText.text.toString(),sharedPref.getUserID())

            api.postMessage(postMessageModel).enqueue(object : retrofit2.Callback<MessageResponseModel>{
                override fun onFailure(call: Call<MessageResponseModel>, t: Throwable) {
                    Toast.makeText(root.context,"Mesaj Gönderimi Başarısız",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MessageResponseModel>, response: Response<MessageResponseModel>) {
                    val data=response.body()
                    if (data!=null){
                        if (data.status=="success"){
                            Toast.makeText(root.context,"Mesaj ${data.info}",Toast.LENGTH_SHORT).show()
                            clearViews()
                        }
                        else{
                            Toast.makeText(root.context,"Mesaj ${data.info}",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(root.context,"Mesaj Gönderimi Başarısız",Toast.LENGTH_SHORT).show()
                    }

                }

            })


        }

        return root
    }

    private fun clearViews() {
        subjectText.text=""
        message.text=""
    }


}
