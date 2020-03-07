package com.octacore.gideonchukwu.view

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.model.Model.CarOwners
import com.octacore.gideonchukwu.view.adapters.CarOwnersRecyclerAdapter
import com.octacore.gideonchukwu.viewmodel.CarOwnerActivityViewModel
import kotlinx.android.synthetic.main.activity_car_owner.*


class CarOwnerActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CarOwnerActivityViewModel::class.java)
    }

    private val filters by lazy {
        intent.getParcelableExtra("extra") as Filters
    }

    private var carOwnersList = mutableListOf<CarOwners>()
    private var filteredCarOwnersList = mutableListOf<CarOwners>()
    private val mAdapter = CarOwnersRecyclerAdapter(filteredCarOwnersList)
    private val requiredPermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private var checkedItemPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_owner)
        carOwnerRecyclerView.adapter = mAdapter
        carOwnerRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        handlePermission()
        registerForContextMenu(carOwnerRecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle(resources.getString(R.string.filter))
        menu?.setHeaderIcon(resources.getDrawable(R.drawable.ic_filter_list))
        menuInflater.inflate(R.menu.filter_context_menu, menu)

        val mi = menu?.getItem(checkedItemPosition)
        // check the Id as you wish
        when (mi?.itemId) {
            R.id.filterName -> {
                mi.isChecked = true
            }
            R.id.filterDate -> {
                mi.isChecked = true
            }
            R.id.filterGender -> {
                mi.isChecked = true
            }
            R.id.filterColour -> {
                mi.isChecked = true
            }
            R.id.filterCountry -> {
                mi.isChecked = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filterMenu -> {
                openContextMenu(carOwnerRecyclerView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filterName -> {
                checkedItemPosition = 0
                filterCarOwners()
            }
            R.id.filterDate -> {
                checkedItemPosition = 1
                filterCarOwners()
            }
            R.id.filterGender -> {
                checkedItemPosition = 2
                filterCarOwners()
            }
            R.id.filterColour -> {
                checkedItemPosition = 3
                filterCarOwners()
            }
            R.id.filterCountry -> {
                checkedItemPosition = 4
                filterCarOwners()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun handlePermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(this, requiredPermission)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, requiredPermission)) {
                showExplanationPermission()
            } else {
                requestPermission()
            }
        } else {
            loadFiles()
        }
    }

    private fun requestPermission() = ActivityCompat.requestPermissions(
        this,
        arrayOf(requiredPermission),
        REQUEST_PERMISSION_READING_STATE
    )

    private fun showExplanationPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permission Needed")
            .setMessage("Rationale")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestPermission()
            }
        builder.create().show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_PERMISSION_READING_STATE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show()
                loadFiles()
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadFiles() {
        viewModel.data.observe(this, Observer { data ->
            carOwnersList.addAll(data)
            filterCarOwners()
        })
    }

    private fun filterCarOwners() {
        when (checkedItemPosition) {
            0 -> {
                viewModel.filterByName(filters, carOwnersList).observe(this, Observer {
                    loadData(it)
                })
            }

            1 -> {
                viewModel.filterByDate(filters, carOwnersList).observe(this, Observer {
                    loadData(it)
                })
            }

            2 -> {
                viewModel.filterByGender(filters, carOwnersList).observe(this, Observer {
                    loadData(it)
                })
            }

            3 -> {
                viewModel.filterByColour(filters, carOwnersList).observe(this, Observer {
                    loadData(it)
                })
            }

            4 -> {
                viewModel.filterByCountry(filters, carOwnersList).observe(this, Observer {
                    loadData(it)
                })
            }
        }
    }

    private fun loadData(data: List<CarOwners>) {
        filteredCarOwnersList.clear()
        filteredCarOwnersList.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    companion object {
        private const val REQUEST_PERMISSION_READING_STATE = 100
        private val TAG = this::class.java.simpleName
    }
}
