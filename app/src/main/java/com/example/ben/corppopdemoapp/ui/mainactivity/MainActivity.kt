package com.example.ben.corppopdemoapp.ui.mainactivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.ben.corppopdemoapp.ImageListAdapter
import com.example.ben.corppopdemoapp.R
import com.example.ben.corppopdemoapp.Utils
import com.example.ben.corppopdemoapp.databinding.ActivityMainBinding
import com.example.ben.corppopdemoapp.model.Photo
import com.example.ben.corppopdemoapp.ui.mapactivity.MapsActivity

/**
 * @class MainActivity
 * Simple activity consisting of a recyclerview and a text input field
 */
class MainActivity : AppCompatActivity(), ImageListAdapter.OnItemClickListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Bind view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        //Setup recycler view with adapter
        binding.rvImages.layoutManager = LinearLayoutManager(this)
        adapter = ImageListAdapter(arrayListOf(), this)
        binding.rvImages.adapter = adapter
        viewModel.imageList.observe(this, Observer<List<Photo>> {
            it?.let {
                adapter.replaceData(it)
            }
        })
    }

    override fun onItemClick(item: Photo) {
        //Start maps activity
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra(Utils.INTENT_EXTRA_IMAGE_ID, item.id)
        this.startActivity(intent)
    }

}
