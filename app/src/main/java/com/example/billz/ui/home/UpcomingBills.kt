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
import com.example.billz.databinding.FragmentUpcomingBillsBinding
import com.example.billz.ui.home.OnClickBill
import com.example.billz.ui.home.UpcomingBillAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpcomingBills.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpcomingBills : Fragment(), OnClickBill {
    private var _binding: FragmentUpcomingBillsBinding? = null
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
        _binding = FragmentUpcomingBillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        billzViewModel.getWeeklyUpcoming().observe(this){upcomingWeekly->
            val adapter = UpcomingBillAdapter(upcomingWeekly, this)
            binding?.rvWeekly?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvWeekly?.adapter = adapter

        }

        billzViewModel.getMonthlyUpcoming().observe(this){upcomingMonthly->
            val adapter = UpcomingBillAdapter(upcomingMonthly, this)
            binding?.rvmonthly?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvmonthly?.adapter = adapter
        }

        billzViewModel.getAnnualUpcoming().observe(this){upcomingAnnual->
            val adapter = UpcomingBillAdapter(upcomingAnnual, this)
            binding?.rvAnnual?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvAnnual?.adapter = adapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up View Binding
        _binding = null


    }

    override fun checkPaidBill(upcomingBill: UpcomingBill) {
        upcomingBill.paid = !upcomingBill.paid
        billzViewModel.updateUpcomingBill(upcomingBill)
    }
}