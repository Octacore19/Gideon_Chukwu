package com.octacore.gideonchukwu.view

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.model.Model.CarOwners
import com.octacore.gideonchukwu.view.adapters.CarOwnersRecyclerAdapter
import com.octacore.gideonchukwu.viewmodel.CarOwnerActivityViewModel
import kotlinx.android.synthetic.main.activity_car_owner.*

class CarOwnerActivity : AppCompatActivity() {
    private lateinit var viewModel: CarOwnerActivityViewModel
    private var carOwnersList = mutableListOf<CarOwners>()
    private val mAdapter = CarOwnersRecyclerAdapter(carOwnersList)
    private val requiredPermission = Manifest.permission.READ_EXTERNAL_STORAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_owner)
        carOwnerRecyclerView.adapter = mAdapter
        carOwnerRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        viewModel = ViewModelProviders.of(this).get(CarOwnerActivityViewModel::class.java)
        handlePermission()
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

    private fun requestPermission() = ActivityCompat.requestPermissions(this, arrayOf(requiredPermission), REQUEST_PERMISSION_READING_STATE)

    private fun showExplanationPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permission Needed")
            .setMessage("Rationale")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestPermission()
            }
        builder.create().show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
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
            mAdapter.notifyDataSetChanged()
        })
    }

    companion object{
        private const val REQUEST_PERMISSION_READING_STATE = 100
    }
}
