package com.clipcodetest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class UserResponse(
    @SerializedName("results")  val dataUser: ArrayList<UserInfo>
)

class UserInfo (
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: NameUser?,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("cell")
    val cell: String,
    @SerializedName("picture")
    val picture: PictureUser?,
    @SerializedName("nat")
    val nat: String?,
    @SerializedName("location")
    val location: LocationUser?

)

@Parcelize
class LocationUser(
    @SerializedName("street")  val street: StreetUser,
    @SerializedName("city")  val city: String,
    @SerializedName("country")  val country: String,
    @SerializedName("postcode")  val postcode: String,
    @SerializedName("state")  val state: String
) : Parcelable{
    fun getStreet(): String {
        return "${street.name} ${street.number} $city $country $postcode $state"
    }
}

@Parcelize
class StreetUser(
    @SerializedName("number")  val number: Int,
    @SerializedName("name")  val name: String,
) : Parcelable

class PictureUser(
    @SerializedName("large")  val large: String,
    @SerializedName("medium")  val medium: String,
    @SerializedName("thumbnail")  val thumbnail: String
)

@Parcelize
class NameUser(
    @SerializedName("title")  val title: String,
    @SerializedName("first")  val first: String,
    @SerializedName("last")  val last: String
) : Parcelable{
    fun getCompleteName(): String {
        return "$first $last"
    }
}