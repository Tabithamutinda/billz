package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.Model.Bill
import com.example.Model.UpcomingBill

@Dao
interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBill(bill: Bill)

    @Query("SELECT * FROM Bills")
    fun getAllBills(): LiveData<List<Bill>>

    @Query ("SELECT * FROM Bills WHERE frequency=:freq")
    fun getRecurringBills(freq:String):List<Bill>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUpcomingBills( vararg upcomingBill: UpcomingBill)

    @Query("SELECT * FROM UpcomingBills WHERE billId =:billId AND dueDate BETWEEN :startDate AND :endDate LIMIT 1")
    fun queryExistingBills(billId:String, startDate:String, endDate:String):List<UpcomingBill>

    @Query("SELECT  * FROM UpcomingBills WHERE frequency = :freq AND paid = :paid ORDER BY dueDate")
    fun getUpcomingBillsByFrequency(freq : String, paid: Boolean=false): LiveData<List<UpcomingBill>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUpcomingBill(upcomingBill: UpcomingBill)

    @Query("SELECT * FROM upcomingbills WHERE paid = :paid ORDER BY dueDate")
    fun getPaidBills(paid: Boolean = true):LiveData<List<UpcomingBill>>
}