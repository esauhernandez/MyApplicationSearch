package com.example.myapplicationsearch.view.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplicationsearch.data.model.SearchResponse
import com.example.myapplicationsearch.domain.usecase.UseCaseRetrofitSearch
import com.example.myapplicationsearch.domain.usecase.interfaces.SearchCallback
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : ViewModel(), SearchCallback, LifecycleObserver{

    val TAG="SearchViewModel"
    var mResponse = MutableLiveData<SearchResponse>()
    var page = 1

    fun performSearch(query: String){
        val useCase = UseCaseRetrofitSearch.mInstance
        useCase.callbackSearch = this
        useCase.urlBase = "https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/"
        useCase.query = query
        useCase.page = page
        useCase.runSearchService()
    }

    override fun onSuccesCallResponse(responseCall: Call<SearchResponse>) {
        Log.d(TAG,"onSuccesCallResponse")
    }

    override fun onComplete() {
        Log.d(TAG,"onComplete")
    }

    override fun onSuccessResponse(response: Response<SearchResponse>) {
        mResponse.value = response.body()
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG,"onSubscribe ${d}")
    }

    override fun onError(e: Throwable) {
        Log.d(TAG,"onError ${e.stackTrace}")
    }

    override fun onFailureResponse(t: Throwable) {
        Log.d(TAG,"onError ${t.stackTrace}")
    }
}