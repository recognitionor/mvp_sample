package com.example.search_user_test.http


import android.net.Uri


object UrlInfo {

    @JvmStatic
    fun getUrl(): String {
        return Uri.Builder()
            .scheme("https")
            .authority("api.github.com")
            .build()
            .toString()
    }

    @JvmStatic
    val URL_HEADER: String = getUrl()

    const val SEARCH_USERS = "/search/users"

}