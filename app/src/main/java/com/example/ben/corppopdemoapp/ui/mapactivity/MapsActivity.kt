package com.example.ben.corppopdemoapp.ui.mapactivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ben.corppopdemoapp.R
import com.example.ben.corppopdemoapp.Utils
import com.example.ben.corppopdemoapp.model.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @class MapsActivity - Activity which just shows an image location on a map
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var viewModel: MapViewModel
    private lateinit var location: Location
    private lateinit var googleMap : GoogleMap
    private var mapReady = false
    private var locationReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        viewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        intent.getStringExtra(Utils.INTENT_EXTRA_IMAGE_ID).let {
            viewModel.showImageLocation(intent.getStringExtra(Utils.INTENT_EXTRA_IMAGE_ID).toString())
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.location.observe(this, Observer<Location> {
            it?.let {
                location = viewModel.location.value!!
                locationReady =true
                    moveToLocation()
                }


        })

    }

    /**
     * If the map is ready and the data is ready with set a marker on the datas position on the map
     */
    private fun moveToLocation() {
        if (mapReady&&locationReady) {
            val imageLocation = LatLng(location.latitude!!.toDouble(), location.longitude!!.toDouble())
            googleMap.addMarker(MarkerOptions().position(imageLocation))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(imageLocation))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        mapReady = true
        moveToLocation()

    }
}
