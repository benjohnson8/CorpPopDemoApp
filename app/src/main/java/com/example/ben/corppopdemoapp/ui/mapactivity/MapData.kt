package com.example.ben.corppopdemoapp.ui.mapactivity

import android.os.Handler
import android.os.Looper
import com.example.ben.corppopdemoapp.Utils.Companion.API_KEY
import com.example.ben.corppopdemoapp.Utils.Companion.API_KEY_PARAM
import com.example.ben.corppopdemoapp.Utils.Companion.BASE_URL
import com.example.ben.corppopdemoapp.Utils.Companion.DEFAULT_RESPONSE_FORMAT
import com.example.ben.corppopdemoapp.Utils.Companion.METHOD_PARAM
import com.example.ben.corppopdemoapp.Utils.Companion.PHOTO_ID_PARAM
import com.example.ben.corppopdemoapp.Utils.Companion.RESPONSE_TYPE_PARAM
import com.example.ben.corppopdemoapp.model.ImageLocationResponse
import com.example.ben.corppopdemoapp.model.Location
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/**
 * @class MapData - Data class for map activity
 */
class MapData {
    private val LOCATION_SEARCH_METHOD: String = "flickr.photos.geo.getLocation"

    /**
     * Default constructor
     */
    val client = OkHttpClient()

    /**
     * Makes a request to obtain data about an images location
     */
    fun searchForLocation(searchQuery: String, dataReadyCallback: IDataReadyCallback) {

        //Build url
        val urlBuilder = HttpUrl.parse(BASE_URL).newBuilder()
        urlBuilder.addQueryParameter(METHOD_PARAM, LOCATION_SEARCH_METHOD)
        urlBuilder.addQueryParameter(API_KEY_PARAM, API_KEY)
        urlBuilder.addQueryParameter(PHOTO_ID_PARAM, searchQuery)
        urlBuilder.addQueryParameter(RESPONSE_TYPE_PARAM, DEFAULT_RESPONSE_FORMAT)
        val request = Request.Builder()
                .url(urlBuilder.build().toString())
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                //Have to manually alter response as it begins with non json text
                var result = response.body().string()
                val gson = GsonBuilder().setLenient().create()
                result = result.substring(14, result.length - 1)

                val locationResult = gson.fromJson(result, ImageLocationResponse::class.java)

                val mainHandler = Handler(Looper.getMainLooper())
                val myRunnable = Runnable {
                    dataReadyCallback.onDataReady(locationResult.photo!!.location!!)
                }
                mainHandler.post(myRunnable)
            }
        })


    }


    /**
     * Callback for when data is available
     */
    interface IDataReadyCallback {
        fun onDataReady(data: Location)
    }
}