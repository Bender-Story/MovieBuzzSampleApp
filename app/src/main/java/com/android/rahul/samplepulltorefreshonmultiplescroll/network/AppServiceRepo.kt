package com.android.rahul.samplepulltorefreshonmultiplescroll.network

import android.annotation.SuppressLint
import com.android.rahul.samplepulltorefreshonmultiplescroll.Constants.API_KEY
import com.android.rahul.samplepulltorefreshonmultiplescroll.Constants.NEWS_API_BASE_URL
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.NowPlayingData

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AppServiceRepo{
    var serviceInterface:ServiceInterface?=null
    init {
        serviceInterface = NetworkAPIController.getApiClient(NEWS_API_BASE_URL)?.create(ServiceInterface::class.java)
    }
    // gets the movie list from service
    fun getMovieList(page:Int, onSuccess: (NowPlayingData) -> Unit,
                     onError: (String) -> Unit){

        serviceInterface!!.fetchNowPalying(page,API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
    // gets the Related movie list from service
    fun getRelatedList(id:String,onSuccess: (NowPlayingData) -> Unit,
                     onError: (String) -> Unit){

        serviceInterface!!.fetchRelated(id,API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
}


