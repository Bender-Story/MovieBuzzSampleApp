package com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result
import com.android.rahul.samplepulltorefreshonmultiplescroll.network.AppServiceRepo

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {
    // related movies list.
    val movieResults: MutableLiveData<List<Result>?> = MutableLiveData()
    // result to show details on the screen
    val result: MutableLiveData<Result> = MutableLiveData()
    val appServiceRepo = AppServiceRepo()

    // call service and fetch related movie list update the movie results mutable list.
    fun fetchRelatedResults(
        id: String, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appServiceRepo.getRelatedList(id, { response ->
            movieResults.postValue(response.results)
            onSuccess.invoke()
        }, {
            onError.invoke(it)
        })
    }

}