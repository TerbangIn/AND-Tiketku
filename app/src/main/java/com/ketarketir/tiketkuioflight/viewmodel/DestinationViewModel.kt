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

        val data = listOf(ListDataDestination(1,"Jakarta", "Bangkok", "Air Asia", "20-30 March 2023", "IDR 950.000", R.drawable.iv_sample_destination, "Wat Arun\n" + "\n" + "Terletak di sisi Thonburi dari Sungai Chao Phraya, Wat Arun (“Kuil Fajar”) adalah salah satu tempat wisata Bangkok tertua dan paling terkenal. Candi ini merupakan representasi arsitektur dari Gunung Meru, pusat alam semesta dalam kosmologi Buddhis. Terlepas dari namanya, pemandangan terbaik Wat Arun adalah pada sore hari dengan matahari terbenam di belakangnya."),
            ListDataDestination(2,"Jakarta", "Belanda", "Garuda Indonesia", "10-20 April 2023", "IDR 1.200.000", R.mipmap.ic_amsterdam_foreground,"Musim semi di Belanda identik dengan Taman Keukenhof. Padang bunga tulip terhampar luas di sini. Ada lebih dari 6 juta umbi tulip yang ditanam di sini setiap tahunnya. Meski di Belanda, lanskap taman bergaya Inggris menambah pesona keindahannya\n" ),
            ListDataDestination(3,"Bali", "Barbados", "Air Asia", "15-25 May 2023", "IDR 1.000..000", R.mipmap.ic_barbados_foreground, "Republik Barbados adalah sebuah negara kepulauan yang terletak di perbatasan Laut Karibia dan Samudra Atlantik. Merangkum satu dari gugusan pulau-pulau Antilles Kecil, Barbados terletak di bagian timur gugusan tersebut.\n" + "\n" + "Barbados masyhur dengan industri pariwisatanya dan dikenal sebagai Little England atau 'Inggris Kecil' kepulauan Karibia. Ini dikarenakan pengaruh Inggris yang kuat di pulau ini. Pulau Barbados terletak 2.585 km sebelah tenggara Miami, Amerika Serikat dan 860 km dari Caracas, Venezuela."),
            ListDataDestination(4,"Jakarta", "Maladewa", "Maldavis Air", "11-30 Juni 2023", "IDR 500.000",R.mipmap.ic_maldives_foreground, "Republik Maladewa adalah sebuah negara kepulauan yang terdiri dari kumpulan atol (suatu pulau koral yang mengelilingi sebuah laguna) di Samudra Hindia. Maladewa terletak di sebelah selatan-barat daya India, sekitar 700 km sebelah barat daya Sri Lanka. Negara ini memiliki 26 atol yang terbagi menjadi 20 atol administratif dan 1 kota. Maladewa merupakan negara dengan populasi dan luas wilayah terkecil di kawasan Asia serta berpenduduk 100 persen beragama Islam.[5] Tinggi rata-rata permukaan tanah di Maladewa adalah 1.5 meter di atas permukaan laut, hal ini menjadikannya negara dengan permukaan terendah di seluruh dunia.[5] Puncak tertinggi Maladewa hanya 2.3 meter di atas permukaan laut sehingga dikenal juga sebagai negara yang memiliki puncak tertinggi paling rendah di dunia.[5] Keadaan ekonomi Maladewa bergantung pada dua sektor utama, yaitu pariwisata dan perikanan.[6] Negara ini sangat dikenal memiliki banyak pantai yang indah dan pemandangan bawah laut yang menarik ± 700.000 turis setiap tahunnya.[6] Penangkapan dan pengolahan ikan menjadikan Maladewa salah satu eksportir ikan ke beberapa negara Asia dan Eropa.[6]"),
            ListDataDestination(6,"Jakarta", "Turki", "Citylink", "17-26 Juli 2023", "IDR 980.000", R.mipmap.ic_hanghia_foreground, "Terletak di Istanbul, Hagia Sophia awalnya adalah sebuah basilika yang dibangun untuk Kaisar Romawi Timur Justinian I pada abad ke-6. \"\\n\" Destinasi ini merupakan hasil karya termegah seorang arsitek asal Romawi dengan ukuran kubah sangat besar berdiameter 31 meter. Gereja ini pernah dijarah oleh tentara salib tahun 1204, lalu menjadi masjid pada abad ke-15 ketika Kaisar Ottoman menaklukan Kota Istanbul. Kemudian, diubah kembali menjadi museum pada tahun 1935 dan sampai sekarang menjadi salah satu daya tarik Istanbul.\\n\" + \"\\n\" + Selain Hagia Sophia, tidak lengkap rasanya mengunjungi Turki tanpa mampir ke Masjid Sultan Ahmed atau Blue Mosque. Destinasi wisata hits luar negeri ini dibangun antara tahun 1609-1616. Memiliki enam menara dan dibangun dengan seni arsitektur luar biasa. Atap-atapnya tinggi dan dilengkapi genteng berwarna biru berjumlah 20.000-an dengan pola yang berbeda. Konon katanya, Blue Mosque didesain untuk menyaingi tetangganya, Hagia Sophia"),
            ListDataDestination(7,"Tokyo", "Seoul", "Korean Air", "16-29 Agustus 2023", "IDR 650.000", R.drawable.iv_sample_destination, "Test")
        )
        _destinations.value = data
    }
}
