package com.example.ben.corppopdemoapp.ui.mainactivity


import android.os.Handler
import android.os.Looper
import com.example.ben.corppopdemoapp.Utils.Companion.API_KEY
import com.example.ben.corppopdemoapp.Utils.Companion.API_KEY_PARAM
import com.example.ben.corppopdemoapp.Utils.Companion.BASE_URL
import com.example.ben.corppopdemoapp.Utils.Companion.DEFAULT_RESPONSE_FORMAT
import com.example.ben.corppopdemoapp.Utils.Companion.DEFAULT_RESULT_SIZE
import com.example.ben.corppopdemoapp.Utils.Companion.METHOD_PARAM
import com.example.ben.corppopdemoapp.model.ImageSearchResponse
import com.example.ben.corppopdemoapp.model.Photo
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


/**
 * Data class to handle image searches
 */
class MainData {
    private val IMAGE_SEARCH_METHOD: String = "flickr.photos.search"

    //Image search parameters
    private val SEARCH_QUEREY_PARAM = "text"
    private val HAS_GEO_PARAM = "has_geo"
    private val RESULT_SIZE_PARAM = "per_page"
    private val RESPONSE_TYPE_PARAM = "format"

    /**
     * Default constructor
     */
    val client = OkHttpClient()

    /**
     * Makes a request to obtain data about nearby places of interest
     */
    fun searchForImages(searchQuery: String, dataReadyCallback: IDataReadyCallback) {


        val urlBuilder = HttpUrl.parse(BASE_URL).newBuilder()
        urlBuilder.addQueryParameter(METHOD_PARAM, IMAGE_SEARCH_METHOD)
        urlBuilder.addQueryParameter(API_KEY_PARAM, API_KEY)
        urlBuilder.addQueryParameter(SEARCH_QUEREY_PARAM, searchQuery)
        urlBuilder.addQueryParameter(HAS_GEO_PARAM, true.toString())
        urlBuilder.addQueryParameter(RESULT_SIZE_PARAM, DEFAULT_RESULT_SIZE.toString())
        urlBuilder.addQueryParameter(RESPONSE_TYPE_PARAM, DEFAULT_RESPONSE_FORMAT)
        val url = urlBuilder.build().toString()

        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                //Have to manually alter response as it begins with non json text
                var result = response.body().string()
                val gson = GsonBuilder().create()
                result = result.substring(14, result.length - 1)
                val mainHandler = Handler(Looper.getMainLooper())
                val myRunnable = Runnable {
                    dataReadyCallback.onDataReady(gson.fromJson(result, ImageSearchResponse::class.java).photo!!.photo!!)
                }
                mainHandler.post(myRunnable)
            }
        })


    }


    /**
     * Callback for when data is available
     */
    interface IDataReadyCallback {
        fun onDataReady(data: List<Photo>)
    }

}


