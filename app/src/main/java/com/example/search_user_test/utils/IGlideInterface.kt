package com.example.search_user_test.utils

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.InputStream

interface IGlideInterface {
    var mGlide: Glide
    var mRequestManager: RequestManager

    fun createGlide(activity: Activity) {
        mRequestManager = Glide.with(activity)
        mGlide = Glide.get(activity)
        replaceRegister(activity)
    }

    fun clearAllCache() {
        /* 메모리는 Main Thread 에서 제거*/
        mGlide.clearMemory()

        /* Disk Cache File 은 워크 Thread 에서 제거*/
        CoroutineScope(Dispatchers.IO).launch {
            mGlide.clearDiskCache()
        }
    }

    fun clearMemoryCache() {
        /* 메모리는 Main Thread 에서 제거*/
        mGlide.clearMemory()
    }

    fun isClearCacheAtActivityDestroy(): Boolean

    fun replaceRegister(activity: Activity) {
        val httpClient = OkHttpClient.Builder()
//        httpClient.connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))

        try {
            httpClient.addInterceptor { chain: Interceptor.Chain? ->

                val request = chain?.request()
                val response = chain?.proceed(request)
                response
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mGlide.registry.replace(
                GlideUrl::class.java, InputStream::class.java,
                OkHttpUrlLoader.Factory(httpClient.build())
        )
    }
}