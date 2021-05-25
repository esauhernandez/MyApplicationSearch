package com.example.myapplicationsearch.data.retrofit.apis

import com.example.myapplicationsearch.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search")
    fun getServiceSearch(@Query("query") query: String?, @Query("page") page: Int?): Call<SearchResponse>

}