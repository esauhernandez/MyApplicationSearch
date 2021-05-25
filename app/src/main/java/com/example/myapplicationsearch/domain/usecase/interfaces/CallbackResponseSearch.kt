package com.example.myapplicationsearch.domain.usecase.interfaces

import com.example.myapplicationsearch.data.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface CallbackResponseSearch : Callback<SearchResponse> {
    override fun onFailure(
        call: Call<SearchResponse>,
        t: Throwable
    )

    override fun onResponse(call: Call<SearchResponse>,
                            response: Response<SearchResponse>
    )
}