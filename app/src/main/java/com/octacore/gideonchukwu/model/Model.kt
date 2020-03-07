package com.octacore.gideonchukwu.model

import android.os.Parcel
import android.os.Parcelable

object Model {

    class Filters(
        val id: String?,
        val avatar: String?,
        val fullName: String?,
        val createdAt: String?,
        val gender: String?,
        val colors: List<String>?,
        val countries: List<String>?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(avatar)
            parcel.writeString(fullName)
            parcel.writeString(createdAt)
            parcel.writeString(gender)
            parcel.writeStringList(colors)
            parcel.writeStringList(countries)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Filters> {
            override fun createFromParcel(parcel: Parcel): Filters {
                return Filters(parcel)
            }

            override fun newArray(size: Int): Array<Filters?> {
                return arrayOfNulls(size)
            }
        }
    }

    class CarOwners(
        val id: String?,
        val firstName: String?,
        val lastName: String?,
        val email: String?,
        val country: String?,
        val carModel: String?,
        val carModelYear: String?,
        val carColor: String?,
        val gender: String?,
        val jobTitle: String?,
        val bio: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(firstName)
            parcel.writeString(lastName)
            parcel.writeString(email)
            parcel.writeString(country)
            parcel.writeString(carModel)
            parcel.writeString(carModelYear)
            parcel.writeString(carColor)
            parcel.writeString(gender)
            parcel.writeString(jobTitle)
            parcel.writeString(bio)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CarOwners> {
            override fun createFromParcel(parcel: Parcel): CarOwners {
                return CarOwners(parcel)
            }

            override fun newArray(size: Int): Array<CarOwners?> {
                return arrayOfNulls(size)
            }
        }
    }
}