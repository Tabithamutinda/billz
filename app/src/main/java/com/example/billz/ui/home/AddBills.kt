package com.example.billz

import BillzViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.Model.Bill
import com.example.Model.Constants
import com.example.ViewModel.UserViewModel
import com.example.billz.databinding.FragmentAddBillsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddBills.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddBills : Fragment() {
    private var _binding: FragmentAddBillsBinding? = null
    private val binding get() = _binding!!
    private var selectedFrequency: Int = 0
    private lateinit var sharedPrefs: SharedPreferences
    val billzViewModel: BillzViewModel by viewModels()
    var selectedMonth = 0
    var selectedDate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddBillsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up View Binding
        _binding = null
    }

//    override fun onResume() {
//        super.onResume()
//        setUpFrequencySpinner()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs = this.requireActivity().getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)

        val items = listOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.spFrequency.setAdapter(adapter)

        binding.addBillButton.setOnClickListener{
            binding.progressBar3.visibility = View.VISIBLE
            saveContact()
        }
    }
    private fun saveContact(){
        var name = binding.billNameInput.text.toString()
        var amount = binding.amountInput.text.toString()
        var frequency = binding.tvFrequency.text.toString()
        var dueDate = binding.tvDuedate.text.toString()
        val userId = sharedPrefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)

        if (name.isEmpty()){
            binding.billNameInput.error = "Enter bill name"
            return
        }
        if (amount.isEmpty()){
            binding.amountInput.error = "Enter bill amount"
            return
        }
        if(frequency == Constants.ANNUAL){
            var finalDate = selectedDate.toString()
            var finalMonth = selectedMonth.toString()
            if (selectedDate<10){
                finalDate = "0$selectedDate"
            }

            if (selectedMonth<10){
                finalMonth = "0$selectedMonth"
            }
            dueDate = "$finalDate/$finalMonth"
        }
        else {
            dueDate = binding.spDateInput.selectedItem.toString()
        }
        binding.progressBar3.visibility = View.VISIBLE
        var bill = Bill(billId = UUID.randomUUID().toString(),
            name= name,
            amount = amount.toDouble(),
            frequency = frequency,
            dueDate = dueDate,
            userId = userId.toString())

        billzViewModel.saveBill(bill)
        resetForm()
    }
    private fun resetForm() {
        binding.billNameInput.setText(Constants.EMPTY_STRING)
        binding.amountInput.setText(Constants.EMPTY_STRING)
        binding.spFrequency.setSelection(0)
        binding.spDateInput.setSelection(0)
    }

    private fun setUpFrequencySpinner(){
        val frequency =
            arrayListOf(
                Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL
            )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, frequency)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = adapter

        binding.spFrequency.onItemClickListener = object: AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(binding.spFrequency.selectedItem.toString()){
                    Constants.WEEKLY -> {
                        setupDueDateSpinner(Array(7){it + 1})
                        hideDatePicker()
                    }
                    Constants.MONTHLY -> {
                        setupDueDateSpinner(Array(31){it + 1})
                        hideDatePicker()
                    }
                    Constants.WEEKLY -> {
                        showDatePicker()
                        setupDueDate()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }

    }

    fun setupDueDateSpinner(dates: Array<Int>) {
        val duedateAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dates)
        duedateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDateInput.adapter = duedateAdapter
    }
    fun showDatePicker() {
        binding.dpduedate.show()
        binding.spDateInput.hide()
    }

    fun hideDatePicker() {
        binding.dpduedate.hide()
        binding.spDateInput.show()
    }
    fun setupDueDate() {
        val calendar = Calendar.getInstance()
        binding.dpduedate.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, month, date ->
            selectedMonth = month + 1
            selectedDate = date
        }
    }

}

private fun View.hide() {
    this.visibility = View.GONE

}
private fun View.show() {
    this.visibility = View.VISIBLE

}


