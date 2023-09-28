package com.example.billz

import BillzViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs =
            this.requireActivity().getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)

        val items = listOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.frequencyInput.setAdapter(adapter)

        val daysOfTheWeek = Array(7){it + 1}
        val daysAdapter = ArrayAdapter(requireContext(), R.layout.list_item, daysOfTheWeek)

        val daysOfTheMonth = Array(31){it + 1}
        val monthAdapter = ArrayAdapter(requireContext(), R.layout.list_item, daysOfTheMonth)

        binding.frequencyInput.setOnItemClickListener { _, _, position, _ ->
            selectedFrequency = position
        }

        binding.dueDateInput.setOnClickListener {

            when (selectedFrequency) {
                0 -> binding.dueDateInput.setAdapter(daysAdapter)

                1 -> binding.dueDateInput.setAdapter(monthAdapter)

                2 -> {
//                    binding.dueDateInput.setAdapter(null)
                    // Show a full calendar
                    val datePickerBuilder =
                        MaterialDatePicker.Builder.datePicker()
                            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                            .setTitleText(Calendar.getInstance().get(Calendar.YEAR).toString())

                    val datePicker = datePickerBuilder.build()

                    datePicker.show(childFragmentManager, null)
                    datePicker.addOnPositiveButtonClickListener {
                        val dateFormat = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
                        binding.dueDateInput.setText(dateFormat.format(it))
                    }
                }
            }

        }
        binding.addBillButton.setOnClickListener{
            binding.progressBar3.visibility = View.VISIBLE
            saveContact()
        }
    }
    private fun saveContact(){
        var name = binding.billNameInput.text.toString()
        var amount = binding.amountInput.text.toString()
        var frequency = binding.frequencyInput.text.toString()
        var dueDate = binding.dueDateInput.text.toString()
        val userId = sharedPrefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)

        if (name.isEmpty()){
            binding.billNameInput.error = "Enter bill name"
            return
        }
        if (amount.isEmpty()){
            binding.amountInput.error = "Enter bill amount"
            return
        }
        if (frequency.isEmpty()){
            binding.frequencyInput.error = "Choose bill frequency"
            return
        }
        if (dueDate.isEmpty()){
            binding.dueDateInput.error = "Choose bill due date"
            return
        }
        binding.progressBar3.visibility = View.VISIBLE
        var bill = Bill(billId = UUID.randomUUID().toString(),
            name= name,
            amount = amount.toDouble(),
            frequency = frequency,
            dueDate = dueDate.toString(),
            userId = userId.toString())

        billzViewModel.saveBill(bill)
        resetForm()
    }
    private fun resetForm() {
        binding.billNameInput.setText(Constants.EMPTY_STRING)
        binding.amountInput.setText(Constants.EMPTY_STRING)
        binding.frequencyInput.setText(Constants.EMPTY_STRING)
        binding.dueDateInput.setText(Constants.EMPTY_STRING)
    }

}


