package com.example.search_user_test.http

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.security.GeneralSecurityException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object HttpsManager {

    /**
     * https://futurestud.io/tutorials/retrofit-2-customize-network-timeouts
     * https://jongmin92.github.io/2018/01/31/Programming/android-customize-network-timeouts/
     */
    const val CONNECTION_TIMEOUT: Long = 10 // TCP handshake가 완료되기까지 지속되는 시간

    const val WRITE_TIMEOUT: Long = 10      // 응답까지의 시간이 읽기 시간 초과보다 크면 요청이 실패로 계산

    const val READ_TIMEOUT: Long = 10       // 읽기 타임 아웃의 반대 방향

    @JvmStatic
    val service: HttpService = setService()

    @JvmStatic
    lateinit var client: OkHttpClient

    @JvmStatic
    private fun setService(): HttpService {
        val builder: OkHttpClient.Builder = configureClient(OkHttpClient().newBuilder())
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        client = builder.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(UrlInfo.URL_HEADER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(HttpService::class.java)
    }

    @JvmStatic
    private fun configureClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val certs = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return arrayOf()
            }

        })
        var ctx: SSLContext? = null

        try {
            ctx = SSLContext.getInstance("TLS")
            ctx.init(null, certs, SecureRandom())
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }

        /**
         * 참고 : https://gist.github.com/maiconhellmann/c61a533eca6d41880fd2b3f8459c07f7
         */
        try {
            builder.sslSocketFactory(ctx!!.socketFactory, certs[0] as X509TrustManager)
                .hostnameVerifier { s, sslSession -> true }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return builder
    }

}

