package com.octacore.gideonchukwu.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.view.adapters.FiltersRecyclerAdapter
import com.octacore.gideonchukwu.viewmodel.FilterActivityViewModel
import kotlinx.android.synthetic.main.activity_filters.*

class FiltersActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(FilterActivityViewModel::class.java) }
    private var filterList = mutableListOf<Filters>()
    private val mAdapter = FiltersRecyclerAdapter(filterList)

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(
                ConnectivityManager
                .EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        filtersRecyclerView.adapter = mAdapter
        filtersRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun disconnected() {
        filtersRecyclerView.visibility = View.GONE
        noInternetTextView.visibility = View.VISIBLE
    }

    private fun connected() {
        filtersRecyclerView.visibility = View.VISIBLE
        noInternetTextView.visibility = View.GONE
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData(){
        viewModel.data.observe(this, Observer { data ->
            filterList.addAll(data)
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}
