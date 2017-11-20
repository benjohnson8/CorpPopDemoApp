package com.example.ben.corppopdemoapp.ui.mapactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ben.corppopdemoapp.model.Location

/**
 * @class MapViewModel - View model for map activity
 */
class MapViewModel : ViewModel(), MapData.IDataReadyCallback {

    //Data class
    private val data = MapData()
    var location = MutableLiveData<Location>()

    /**
     * Makes a request to obtain the images location details
     */
    fun showImageLocation(imageId: String) {
        data.searchForLocation(imageId, this)
    }

    override fun onDataReady(geolocation: Location) {
        geolocation.let {
            location.value = geolocation
        }
    }

}