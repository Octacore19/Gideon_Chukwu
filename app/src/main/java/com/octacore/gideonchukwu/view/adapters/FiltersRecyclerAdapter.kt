package com.octacore.gideonchukwu.view.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.R
import com.octacore.gideonchukwu.view.CarOwnerActivity

class FiltersRecyclerAdapter(private val filterList: List<Filters>): RecyclerView.Adapter<FiltersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.filters_recycler_item, parent, false))
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(filterList[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val avatarView: ImageView = itemView.findViewById(R.id.avatarImageView)
        private val fullNameView: TextView = itemView.findViewById(R.id.fullNameTextView)
        private val genderView: TextView = itemView.findViewById(R.id.genderTextView)
        private val creationDateView: TextView = itemView.findViewById(R.id.creationDateTextView)
        private val colorsView: TextView = itemView.findViewById(R.id.colorsTextView)
        private val countriesView: TextView = itemView.findViewById(R.id.countriesTextView)

        fun bindView(data: Filters, position: Int){
            Glide.with(itemView)
                .load(Uri.parse(data.avatar))
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(CircleCrop())
                .into(avatarView)

            fullNameView.text = data.fullName
            genderView.text = data.gender.capitalize()
            creationDateView.text = data.createdAt

            val colorBuilder = StringBuilder()
            data.colors.forEach {
                colorBuilder.append(it).append(", ")
            }
            colorsView.text = colorBuilder

            val countryBuilder = StringBuilder()
            data.countries.forEach {
                countryBuilder.append(it).append(", ")
            }
            countriesView.text = countryBuilder

            if (colorBuilder.isNullOrEmpty()){
                itemView.findViewById<LinearLayout>(R.id.coloursLayout).visibility = View.GONE
            }
            if (countryBuilder.isNullOrEmpty()){
                itemView.findViewById<LinearLayout>(R.id.countriesLayout).visibility = View.GONE
            }

            itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, CarOwnerActivity::class.java))
            }
        }
    }
}