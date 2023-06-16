import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.R
import com.ketarketir.tiketkuioflight.model.destination.ListDataDestination

class DestinationViewModel : ViewModel() {
    private val _destinations = MutableLiveData<List<ListDataDestination>>()
    val destinations: LiveData<List<ListDataDestination>>
        get() = _destinations

    init {
        loadDestinations()
    }

    private fun loadDestinations() {
        // Simulate loading data from a data source
        val data = listOf(
            ListDataDestination(
                "Jakarta",
                "Bangkok",
                "Air Asia",
                "20-30 March 2023",
                "IDR 950",
                R.drawable.iv_sample_destionation
            ),
            ListDataDestination(
                "Bali",
                "Singapore",
                "Singapore Airlines",
                "10-20 April 2023",
                "IDR 950",
                R.drawable.iv_sample_destionation
            ),
            ListDataDestination(
                "Tokyo",
                "Seoul",
                "Korean Air",
                "15-25 May 2023",
                "IDR 950",
                R.drawable.iv_sample_destionation
            )
            // Add more data items as needed
        )
        _destinations.value = data
    }
}
