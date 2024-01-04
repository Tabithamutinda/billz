import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Model.Bill
import com.example.Model.MyBills
import com.example.Model.UpcomingBill
import com.example.utils.Constants
import kotlinx.coroutines.launch

class BillzViewModel : ViewModel() {

    val billsRepository = BillzRepository()
    lateinit var BillsLiveData: LiveData<Bill>

            fun saveBill (bill:Bill){
                viewModelScope.launch {
                    billsRepository.saveBill(bill)
                }
            }

    fun getAllBills (){
        viewModelScope.launch {
            billsRepository.getAllBills()
        }
    }

    fun createUpcomingBills(){
        viewModelScope.launch {
            billsRepository.createRecurringWeeklyBills()
            billsRepository.createRecurringMonthlyBills()
            billsRepository.createRecurringAnnualBills()
        }
    }

    fun getWeeklyUpcoming(): LiveData<List<UpcomingBill>>{
        return  billsRepository.getUpcomingBillsByFrequency(Constants.WEEKLY)
    }

    fun getMonthlyUpcoming(): LiveData<List<UpcomingBill>>{
        return  billsRepository.getUpcomingBillsByFrequency(Constants.MONTHLY)
    }

    fun getAnnualUpcoming(): LiveData<List<UpcomingBill>>{
        return  billsRepository.getUpcomingBillsByFrequency(Constants.ANNUAL)
    }

    fun updateUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepository.updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBills():LiveData<List<UpcomingBill>>{
        return billsRepository.getPaidBIlls()
    }
}