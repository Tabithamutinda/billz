package com.example.database

import com.example.BillzApp
import com.example.Model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    val database = BillsDb.getDatabase(BillzApp.appContext)
    suspend fun saveBill (bill: Bill){
        withContext(Dispatchers.IO){
            database.BillsDao().addBill(bill)
        }
    }
}