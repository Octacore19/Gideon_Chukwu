package com.octacore.gideonchukwu.model

object RemoteModel {
    data class Filters(val id: String,
                       val avatar: String,
                       val fullName: String,
                       val createdAt: String,
                       val gender: String,
                       val colors: Array<String>,
                       val countries: Array<String>) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Filters

            if (id != other.id) return false
            if (avatar != other.avatar) return false
            if (fullName != other.fullName) return false
            if (createdAt != other.createdAt) return false
            if (gender != other.gender) return false
            if (!colors.contentEquals(other.colors)) return false
            if (!countries.contentEquals(other.countries)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id.hashCode()
            result = 31 * result + avatar.hashCode()
            result = 31 * result + fullName.hashCode()
            result = 31 * result + createdAt.hashCode()
            result = 31 * result + gender.hashCode()
            result = 31 * result + colors.contentHashCode()
            result = 31 * result + countries.contentHashCode()
            return result
        }
    }
}