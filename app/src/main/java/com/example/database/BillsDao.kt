package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.Model.Bill

@Dao
interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBill(bill: Bill)

    @Query("SELECT * FROM Bills")
    fun getAllBills(): LiveData<List<Bill>>
}