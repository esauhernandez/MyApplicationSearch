package com.example.myapplicationsearch.domain.usecase

import com.example.myapplicationsearch.data.repository.RetrofitSearchRepository
import com.example.myapplicationsearch.domain.mapper.SearchResponseMapper
import com.example.myapplicationsearch.domain.usecase.interfaces.SearchCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UseCaseRetrofitSearch {

    lateinit var callbackSearch: SearchCallback
    var query: String = ""
    var page = 1
    lateinit var urlBase:String
    companion object{
        val mInstance = UseCaseRetrofitSearch()
    }

    fun runSearchService(): Disposable {
        return RetrofitSearchRepository.getQuerySearch(urlBase)
            .map { retrofit->
                SearchResponseMapper.mapper(retrofit, query, page, callbackSearch ) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {callbackSearch.onSuccesCallResponse(it)}
                ,{callbackSearch.onError(it)}
                ,{callbackSearch.onComplete()})
    }

}