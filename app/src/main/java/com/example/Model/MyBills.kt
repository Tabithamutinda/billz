package com.example.Model

import com.google.gson.annotations.SerializedName

data class MyBills(
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("due_date")
    var dueDate: String,
    var name: String,
    var amount: Double,
    var frequency: String,
    @SerializedName("bill_id")
    var billId: String
)
