package com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.NowPlayingData
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result
import com.android.rahul.samplepulltorefreshonmultiplescroll.network.AppServiceRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val movieResults: MutableLiveData<List<Result>?> = MutableLiveData()
    val appServiceRepo = AppServiceRepo()
    var nowPlayingData:NowPlayingData?=null
    var fetchPage:Int=1

    // call service to fetch movie list and update the mutable list.
    fun fetchMovieResults(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appServiceRepo.getMovieList(fetchPage,{ response ->
            nowPlayingData=response
            movieResults.postValue(nowPlayingData?.results)
            fetchPage= (nowPlayingData?.page!!)+1
            onSuccess.invoke()
        }, {
            onError.invoke(it)
        })
    }

    fun isMaxPageLimitReached():Boolean?{
        return fetchPage > nowPlayingData?.total_pages ?:0
    }

}