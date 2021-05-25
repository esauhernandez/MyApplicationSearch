package com.example.myapplicationsearch.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object{
        private val mInstance= RetrofitClient()
        fun getClient(): OkHttpClient= mInstance.getClient()
    }
    private fun getClient(): OkHttpClient{
        val client= OkHttpClient.Builder()
        client.connectTimeout(5, TimeUnit.MINUTES)
        client.readTimeout(5, TimeUnit.MINUTES)
        client.writeTimeout(5, TimeUnit.MINUTES)
        client.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .header("X-IBM-Client-Id", "adb8204d-d574-4394-8c1a-53226a40876e")
                    .header("User-Agent", "Retrofit-Sample-App")
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()

                return chain.proceed(request)

            }
        })


        return client.build()
    }
}