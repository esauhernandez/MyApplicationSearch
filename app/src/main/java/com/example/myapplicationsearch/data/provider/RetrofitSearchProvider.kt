package com.example.myapplicationsearch.data.provider

import com.example.myapplicationsearch.data.retrofit.RetrofitBuilder
import com.example.myapplicationsearch.data.retrofit.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSearchProvider {
    lateinit var mUrlBase:String
    companion object{
        private val mInstance = RetrofitSearchProvider()
        fun getQuerySearch(urlBase:String): Retrofit =
            mInstance.getQuerySearch(urlBase)
    }

    private fun getQuerySearch(urlBase:String): Retrofit {
        mUrlBase=urlBase
        val builder= RetrofitBuilder.getBuilder()
        val client = RetrofitClient.getClient()

        return builder.baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}