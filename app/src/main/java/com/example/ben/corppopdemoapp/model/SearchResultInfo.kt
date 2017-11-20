package com.example.ben.corppopdemoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * @class SearchResultInfo is the model for handling the information about the image search response
 */
class SearchResultInfo {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("pages")
    @Expose
    var pages: Int? = null
    @SerializedName("perpage")
    @Expose
    var perpage: Int? = null
    @SerializedName("total")
    @Expose
    var total: String? = null
    @SerializedName("photo")
    @Expose
    var photo: List<Photo>? = null

}
