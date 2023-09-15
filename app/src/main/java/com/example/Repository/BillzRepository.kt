

import com.example.BillzApp
import com.example.Model.Bill
import com.example.database.BillsDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillzRepository {
    //initialize the database
    val database = BillsDb.getDatabase(BillzApp.appContext)
    suspend fun saveBill (bill: Bill){
        withContext(Dispatchers.IO){
            database.BillsDao().addBill(bill)
        }
    }
}