package com.example.ben.corppopdemoapp.ui.mainactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.ObservableField
import com.example.ben.corppopdemoapp.model.Photo

/**
 * @class MainViewModel - view model for main activity
 */
class MainViewModel : ViewModel(), MainData.IDataReadyCallback {

    //data class
    private val data = MainData()
    //observable edit text for search
    var searchText = ObservableField<String>()

    var imageList = MutableLiveData<List<Photo>>()

    /**
     * Makes a request to search for images with the given search term
     */
    fun searchForImages() {
        data.searchForImages(searchText.get(), this)
    }

    /**
     * Request was succesfull and there are places to show the user
     * @param data is the results from the request
     */
    override fun onDataReady(data: List<Photo>) {
        if (data.isEmpty())
            return
        imageList.value = data
    }


}