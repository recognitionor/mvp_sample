package com.example.search_user_test.http

/**
 * Created by no.1 on 2017-08-25.
 */


import com.example.search_user_test.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {

    @GET(UrlInfo.SEARCH_USERS)
    fun searchUsers(@Query("q") q: String): Observable<User>

}