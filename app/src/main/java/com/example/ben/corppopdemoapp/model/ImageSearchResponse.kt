package com.example.ben.corppopdemoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @class ImageSearchResponse is the model for handling the image search
 */
class ImageSearchResponse {

    @SerializedName("photos")
    @Expose
    var photo: SearchResultInfo? = null
    @SerializedName("stat")
    @Expose
    var stat: String? = null

}
