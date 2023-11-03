package com.example.puiyeeng_coleanam_mapd711_assignment3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker
import com.example.puiyeeng_coleanam_mapd711_assignment3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding

    // Create restaurant Class
    class Restaurant(var city: String, var name: String, var address: String, var lat:Double, var long:Double, var openingHours: String)

    // Create a list to store Restaurant objects
    var restaurantList = mutableListOf<Restaurant>()

    // Add restaurant function
    fun addRestaurant(city: String, name: String, address: String, lat:Double, long:Double, openingHours: String) {
        val restaurant = Restaurant(city, name, address, lat, long, openingHours)
        restaurantList.add(restaurant)
    }

    // Get restaurant function
    fun getRestaurantByCity(city: String): List<Restaurant>? {
        val specificRestaurants = mutableListOf<Restaurant>()
        for (restaurant in restaurantList) {
            if (restaurant.city == city) {
                specificRestaurants.add(restaurant)
            }
        }
        return specificRestaurants
    }

    // Create City Class
    class City(var city: String, var lat:Double, var long:Double)

    // Create a list to store City objects
    var cityList = mutableListOf<City>()

    // Add City function
    fun addCity(city: String, lat:Double, long:Double) {
        val city = City(city, lat, long)
        cityList.add(city)
    }

    // Get City function
    fun getCityByName(city: String): City? {
        val city = cityList.find { it.city == city }
        return city
    }

    // create markerList to store marker history
    val markerList = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //java reference "search" button object for button control
        val search: Button = findViewById(R.id.search)

        //java reference "citySpinner" object for spinner control
        val citySpinner: Spinner = findViewById(R.id.citySpinner)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Create and add City objects to the City list
        addCity("Scarborough",43.777702,-79.233238)
        addCity("Toronto Downtown",43.651070,-79.347015)
        addCity("Mississauga",43.595310,-79.640579)
        addCity("Oakville", 43.45011,-79.68292)
        addCity("North York",43.76153,-79.411079)



        // Create and add Restaurant objects to the Restaurant list
        addRestaurant("Scarborough", "Pizza Hut Scarborough", "259 Morningside Ave, Scarborough, ON M1E 2M9",43.77394948907918, -79.18650444859026,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Scarborough", "Pizza Pizza", "9390 Sheppard Ave E, Scarborough, ON M1B 5R5",43.81015540696113, -79.17711340912099,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Scarborough", "Pizzaville", "3601 Lawrence Ave E, Scarborough, ON M1G 1P5",43.759983024907314, -79.22308873000904,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("Scarborough", "Pizza On Fire", "880 Ellesmere Rd Unit 10, Scarborough, ON M1P 2L8",43.77040475312448, -79.2829760414697,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("Scarborough", "Scarborough Pizza Kabob", "2157 Lawrence Ave E, Scarborough, ON M1P 2P5",43.75202200813325, -79.26700909454824,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Mississauga", "Pizza Pizza", "1250 Eglinton Ave W, Mississauga, ON L5V 1N3",43.583655029241676, -79.68331554113073,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Mississauga", "Pizza Hut", "1900 Fowler Dr, Mississauga, ON L5K 0A1",43.531596695350615, -79.64658019665042,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Mississauga", "Domino's Pizza", "4040 Creditview Rd, Mississauga, ON L5C 3Y8",43.57284194426748, -79.6661497852168,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("Mississauga", "Marconi Pizza", "3635 Cawthra Rd, Mississauga, ON L5A 2Y5",43.617094838593594, -79.61925606663655,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("Mississauga", "DoughBox Wood Fired Pizza & Pasta", "5955 Latimer Dr, Mississauga, ON L5V 0B7",43.613183844381645, -79.69535175219349,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("North York", "Pizza Nova", "879 York Mills Rd, Toronto, ON M3B 1Y5",43.7567874490461, -79.34869552936054,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("North York", "Pizza Pizza", "738 Sheppard Ave E, North York, ON M2K 1C4",43.77330001278007, -79.37668612187372,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("North York", "Domino's Pizza", "820 Sheppard Ave W, Toronto, ON M3H 2T1",43.75737170080432, -79.450874184608982,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("North York", "Pizza Hut North York", "3555 Don Mills Rd, North York, ON M2H 3N3",43.799837775261594, -79.35329349144726,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("North York", "Blaze Pizza", "4841 Yonge St, Toronto, ON M2N 5X2",43.76702572974226, -79.41010416762245,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Oakville", "Gino's Pizza", "1289 Marlborough Ct, Oakville, ON L6H 2R9",43.47324603029097, -79.69333717749052,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("Oakville", "Double Double Pizza & Chicken", "2530 Sixth Line, Oakville, ON L6H 6W5",43.48012910575361, -79.72662533741885,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("Oakville", "Pizza Nova", "1133 Monastery Dr, Oakville, ON L6M 2A3",43.447347937395016, -79.72680245555834,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Oakville", "Vili's Pizza & Shawarma And Wings", "562 Kerr St, Oakville, ON L6K 3C7",43.45081972601985, -79.68943067086398,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Oakville", "Big Taste Pizza", "2165 Grosvenor St, Oakville, ON L6H 7K9",43.49362206721472, -79.6956297825188,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("Toronto Downtown", "Za Cafe Pizzeria & Bar", "372 Bay St., Toronto, ON M5H 2W9",43.65246673121666, -79.38139772717668,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("Toronto Downtown", "Pizza Pizza", "471 Yonge St, Toronto, ON M4Y 1A1",43.66323050122609, -79.38333392520497,"10:00 a.m. to 09:00 p.m.")
        addRestaurant("Toronto Downtown", "Panago Pizza", "133 Bremner Blvd, Toronto, ON M5J 3A7",43.643851590186294, -79.38368772608715,"10:00 a.m. to 11:00 p.m.")
        addRestaurant("Toronto Downtown", "Pizza Gigi", "189 Harbord St, Toronto, ON M5S 1H5",43.663617735975706, -79.40953259149738,"11:00 a.m. to 11:00 p.m.")
        addRestaurant("Toronto Downtown", "Pizzaiolo", "1 Toronto St, Toronto, ON M5C 2V6",43.65187998507932, -79.3762349706368,"11:00 a.m. to 11:00 p.m.")

        // when search button is pressed
        search.setOnClickListener {
            var selectedCity = citySpinner.getSelectedItem().toString();

            mapFragment.getMapAsync { googleMap ->
                mMap = googleMap

                // Remove all previous markers
                for (marker in markerList) {
                    marker.remove()
                }
                markerList.clear()

                // Adds custom info window to markers that have been clicked
                mMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))

                // Get the selected city lat long and move the camera to the selected city
                val city = getCityByName(selectedCity)
                val lat = city?.lat
                val long = city?.long
                if (lat != null && long != null) {
                    val location = LatLng(lat, long)
                    // move the camera and zoom the map
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
                }

                // Get the selected city's pizza restaurants and add the marker
                val restaurants = getRestaurantByCity(selectedCity)
                if (restaurants != null) {
                    for (restaurant in restaurants){
                        val lat = restaurant?.lat
                        val long = restaurant?.long
                        val name = restaurant?.name
                        if (name!= null && lat != null && long != null) {

                            val location = LatLng(lat, long)
                            // Add markers and add into markerList for record
                            mMap.addMarker(MarkerOptions().position(location).title(name).snippet(restaurant.address + "\n" + lat + " " + long + "\n" + "Opening Hours: " + restaurant.openingHours))
                                ?.let { it1 -> markerList.add(it1) }

                        }
                    }
                }
            }
        }
    }

//    internal inner class CustomInfoWindowAdapter : InfoWindowAdapter {
//
////        private val window: View = layoutInflater.inflate(R.layout.restaurant_info_window, null)
//        private val contents: View = layoutInflater.inflate(R.layout.restaurant_info_window, null)
//        override fun getInfoContents(p0: Marker): View? {
//            TODO("Not yet implemented")
//        }
//
//        override fun getInfoWindow(p0: Marker): View? {
//
//        }
//
//    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        // Add a marker in Toronto and move the camera
        val toronto = LatLng(43.651070, -79.347015)
        mMap.addMarker(MarkerOptions().position(toronto).title("Toronto"))
            ?.let { it1 -> markerList.add(it1) }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toronto, 10f))

    }
}