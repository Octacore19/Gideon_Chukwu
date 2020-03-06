package com.octacore.gideonchukwu.model

object Model {

    data class Filters(
        val id: String,
        val avatar: String,
        val fullName: String,
        val createdAt: String,
        val gender: String,
        val colors: List<String>,
        val countries: List<String>
    )

    data class CarOwners(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val country: String,
        val carModel: String,
        val carModelYear: String,
        val carColor: String,
        val gender: String,
        val jobTitle: String,
        val bio: String
    )
}