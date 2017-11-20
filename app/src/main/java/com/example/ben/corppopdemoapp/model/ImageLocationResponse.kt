package com.example.ben.corppopdemoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @class ImageLocationResponse is the model for handling the image location search
 */
class ImageLocationResponse {

    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

}