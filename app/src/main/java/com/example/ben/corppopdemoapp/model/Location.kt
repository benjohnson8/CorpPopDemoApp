package com.example.ben.corppopdemoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @class Location - Holds the images location data
 */
class Location {
    @SerializedName("latitude")
    @Expose
    var latitude: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: String? = null
}
