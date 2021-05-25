package com.example.myapplicationsearch.domain.usecase.interfaces

import com.example.myapplicationsearch.data.model.SearchResponse
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Response

interface SearchCallback {
    //fun onNextCallResponse(response: SearchResponse)
    fun onSuccesCallResponse(responseCall: Call<SearchResponse>)
    fun onComplete()
    fun onSuccessResponse(response: Response<SearchResponse>)
    fun onSubscribe(d: Disposable)
    fun onError(e: Throwable)
    fun onFailureResponse(t: Throwable)
}