package com.example.billz

import BillzViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.Api.ApiClient
import com.example.Api.ApiInterface
import com.example.utils.Constants
import com.example.billz.databinding.FragmentSummaryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Summary.newInstance] factory method to
 * create an instance of this fragment.
 */
class Summary : Fragment() {
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    private var selectedFrequency: Int = 0
    private lateinit var sharedPrefs: SharedPreferences
    val billzViewModel: BillzViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs = this.requireActivity().getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
    }

    fun getAllBills(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getMyBills()
//        request.enqueue(object : Callback<List<Bill>>{
//            override fun onResponse(call: Call<List<Bill>>, response: Response<List<Bill>>) {
//                if (response.isSuccessful) {
//                    val bills = response.body()
//                    if (bills != null) {
//                        // Process the list of bills here
//                        Toast.makeText(context, "${bills.size} bills", Toast.LENGTH_SHORT).show()
//                    } else {
//                        // Handle the case where the response body is null
//                        Toast.makeText(context, "Empty response body", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    // Handle the case where the response is not successful
//                    Toast.makeText(context, "Failed to get bills. Error code: ${response.code()}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Bill>>, t: Throwable) {
//                // Handle network errors or failures here
//                Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })

    }
}