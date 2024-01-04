

import androidx.lifecycle.LiveData
import com.example.BillzApp
import com.example.Model.Bill
import com.example.utils.Constants
import com.example.Model.UpcomingBill
import com.example.database.BillsDb
import com.example.utils.DateTimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class BillzRepository {
    //initialize the database
    val database = BillsDb.getDatabase(BillzApp.appContext)
    suspend fun saveBill (bill: Bill){
        withContext(Dispatchers.IO){
            database.BillsDao().addBill(bill)
        }
    }
    suspend fun getAllBills(){
        withContext(Dispatchers.IO){
            database.BillsDao().getAllBills()
        }
    }

    suspend fun insertUpcomingBills(upcomingBill: UpcomingBill){
        withContext(Dispatchers.IO){
            database.BillsDao().insertUpcomingBills(upcomingBill)
        }
    }

    suspend fun createRecurringMonthlyBills() {
        withContext(Dispatchers.IO) {
            val montlyBills = database.BillsDao().getRecurringBills(Constants.MONTHLY)
            montlyBills.forEach { bill ->
                val startDate = DateTimeUtils.getFirstDayOfMonth()
                val endDate = DateTimeUtils.getLatsDateOfMOnth()


                val exisitng = database.BillsDao().queryExistingBills(bill.billId, startDate, endDate)
                if (exisitng.isEmpty()) {
                    val newUpcomingBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId = bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getFullDateFromDay(bill.dueDate),
                        userId = bill.userId,
                        paid = false
                    )
                    database.BillsDao().insertUpcomingBills(newUpcomingBill)
                }
            }
        }
    }


    suspend fun createRecurringWeeklyBills() {
        withContext(Dispatchers.IO) {
            val weeklyBills = database.BillsDao().getRecurringBills(Constants.WEEKLY)
            val startDate = DateTimeUtils.getFirstDayOfWeek()
            val endDate = DateTimeUtils.getLastDayOfWeek()
            weeklyBills.forEach { bill ->
                val existing = database.BillsDao().queryExistingBills(bill.billId, startDate, endDate)
                if (existing.isEmpty()) {
                    val newUpcomingWeeklyBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId = bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDateOfWeekDay(bill.dueDate),
                        userId = bill.userId,
                        paid = false
                    )
                    database.BillsDao().insertUpcomingBills(newUpcomingWeeklyBill)
                }
            }
        }
    }
    suspend fun createRecurringAnnualBills(){
        withContext(Dispatchers.IO){
            val annualBills = database.BillsDao().getRecurringBills(Constants.ANNUAL)
            val year = DateTimeUtils.getCurrentYear()
            val startDate = "$year-001-01"
            val endDate = "$year-12-31"
            annualBills.forEach{ bill ->
                val existing = database.BillsDao().queryExistingBills(bill.billId, startDate, endDate)
                if (existing.isEmpty()){
                    val newAnnualBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId = bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = "${bill.dueDate}/$year",
                        userId = bill.userId,
                        paid = false
                    )
//                    save the bill
                    database.BillsDao().insertUpcomingBills(newAnnualBill)

                }
            }
        }
    }

    fun getUpcomingBillsByFrequency (frequency: String):LiveData<List<UpcomingBill>>{
        return database.BillsDao().getUpcomingBillsByFrequency(frequency)
    }

    suspend fun updateUpcomingBill(upcomingBill: UpcomingBill){
        withContext(Dispatchers.IO){
            database.BillsDao().updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBIlls(): LiveData<List<UpcomingBill>> {
        return database.BillsDao().getPaidBills()
    }
}