package com.example.billz

import BillzViewModel
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Model.UpcomingBill
import com.example.billz.databinding.FragmentPaidBillsBinding
import com.example.billz.ui.home.OnClickBill
import com.example.billz.ui.home.UpcomingBillAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaidBills.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaidBills : Fragment(), OnClickBill {

    private var _binding: FragmentPaidBillsBinding? = null
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
        _binding = FragmentPaidBillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        billzViewModel.getPaidBills().observe(this){paidbills ->
            val adapter = UpcomingBillAdapter(paidbills, this)
            //we call this because the adapter is on the type onClickBill and the Fragment class. The fragment has the function the adapter needs to call (checkPaidBill function)
            binding?.rvPaid?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvPaid?.adapter = adapter

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun checkPaidBill(upcomingBill: UpcomingBill) {
        upcomingBill.paid = !upcomingBill.paid
        billzViewModel.updateUpcomingBill(upcomingBill)
    }


}