package com.example.search_user_test.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import androidx.room.PrimaryKey

@Entity(tableName = "users")
@Parcelize
data class UserItem(
    @PrimaryKey
    var id: Int = 0,
    var login: String? = null,
    var node_id: String? = null,
    var avatar_url: String? = null,
    var gravatar_id: String? = null,
    var url: String? = null,
    var html_url: String? = null,
    var followers_url: String? = null,
    var following_url: String? = null,
    var gists_url: String? = null,
    var starred_url: String? = null,
    var subscriptions_url: String? = null,
    var organizations_url: String? = null,
    var repos_url: String? = null,
    var events_url: String? = null,
    var received_events_url: String? = null,
    var type: String? = null,
    var site_admin: Boolean = false,
    var score: Float = 0f,
    var checked: Boolean = false
) : Parcelable