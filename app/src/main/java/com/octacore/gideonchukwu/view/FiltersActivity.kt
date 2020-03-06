package com.octacore.gideonchukwu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.view.adapters.FiltersRecyclerAdapter
import com.octacore.gideonchukwu.viewmodel.FilterActivityViewModel
import kotlinx.android.synthetic.main.activity_filters.*

class FiltersActivity : AppCompatActivity() {
    private lateinit var viewModel: FilterActivityViewModel
    private var filterList = mutableListOf<Filters>()
    private val mAdapter = FiltersRecyclerAdapter(filterList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        filtersRecyclerView.adapter = mAdapter
        filtersRecyclerView.layoutManager = LinearLayoutManager(applicationContext)

        viewModel = ViewModelProviders.of(this).get(FilterActivityViewModel::class.java)
        viewModel.data.observe(this, Observer { data ->
            filterList.addAll(data)
            mAdapter.notifyDataSetChanged()
        })
    }
}
