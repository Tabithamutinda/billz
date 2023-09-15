import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Model.Bill
import kotlinx.coroutines.launch

class BillzViewModel : ViewModel() {

    val billsRepository = BillzRepository()
    lateinit var BillsLiveData: LiveData<Bill>

            fun saveBill (bill:Bill){
                viewModelScope.launch {
                    billsRepository.saveBill(bill)
                }
            }
}