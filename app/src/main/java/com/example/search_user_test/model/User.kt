package com.example.search_user_test.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    @SerializedName("total_count")
    var total_count: Int = 0,

    @SerializedName("incomplete_results")
    var incomplete_results: Boolean = false,

    var items: ArrayList<UserItem> = arrayListOf()
) : Parcelable