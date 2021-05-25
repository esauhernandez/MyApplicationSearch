package com.example.myapplicationsearch.domain.mapper

import com.example.myapplicationsearch.data.model.SearchResponse
import com.example.myapplicationsearch.data.retrofit.apis.SearchService
import com.example.myapplicationsearch.domain.usecase.interfaces.SearchCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchResponseMapper {

    var responseSe: SearchResponse? = null
    companion object{
        val mInstance=SearchResponseMapper()
        fun mapper(retrofit: Retrofit, query: String, page: Int,
                   callbackPerfilGrupos: SearchCallback) =
            mInstance.mapper(retrofit, query, page, callbackPerfilGrupos)

    }


    private fun mapper(retrofit: Retrofit, query: String, page: Int, callback: SearchCallback ): Call<SearchResponse> {
        val reService = retrofit.create(SearchService::class.java)
        val answerRECall = reService.getServiceSearch(query, page)
        try {
            answerRECall.enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        responseSe = response.body()
                    }
                    callback.onSuccessResponse(response)
                }

                override fun onFailure(
                    call: Call<SearchResponse>,
                    t: Throwable
                ) {
                    if (t.message != "") {
                    }
                    callback.onFailureResponse(t)
                }
            })
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }

        return answerRECall
    }

}