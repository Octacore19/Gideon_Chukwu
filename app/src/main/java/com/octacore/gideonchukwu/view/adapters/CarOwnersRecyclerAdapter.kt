package com.octacore.gideonchukwu.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.model.Model.CarOwners

class CarOwnersRecyclerAdapter(private val carOwnersList: MutableList<CarOwners>) :
    RecyclerView.Adapter<CarOwnersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.car_owners_recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return carOwnersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(carOwnersList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val firstNameView: TextView = itemView.findViewById(R.id.firstNameTextView)
        private val lastNameView: TextView = itemView.findViewById(R.id.lastNameTextView)
        private val genderView: TextView = itemView.findViewById(R.id.genderTextView)
        private val emailView: TextView = itemView.findViewById(R.id.emailTextView)
        private val countryView: TextView = itemView.findViewById(R.id.countryTextView)
        private val carModelView: TextView = itemView.findViewById(R.id.carModelTextView)
        private val yearView: TextView = itemView.findViewById(R.id.yearTextView)
        private val carColorView: TextView = itemView.findViewById(R.id.carColorTextView)
        private val jobTitleView: TextView = itemView.findViewById(R.id.jobTitleTextView)
        private val bioView: TextView = itemView.findViewById(R.id.bioTextView)

        fun bindViews(data: CarOwners) {
            firstNameView.text = data.firstName
            lastNameView.text = data.lastName
            genderView.text = data.gender
            emailView.text = data.email
            countryView.text = data.country
            carModelView.text = data.carModel
            yearView.text = data.carModelYear
            carColorView.text = data.carColor
            jobTitleView.text = data.jobTitle
            bioView.text = data.bio
        }
    }
}