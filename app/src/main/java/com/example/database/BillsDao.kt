package com.example.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.Model.Bill

interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBill(bill: Bill)
}