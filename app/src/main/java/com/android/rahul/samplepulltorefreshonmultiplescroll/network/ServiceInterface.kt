package com.android.rahul.samplepulltorefreshonmultiplescroll.network

import com.android.rahul.samplepulltorefreshonmultiplescroll.model.NowPlayingData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceInterface{
    @GET("3/movie/now_playing/")
    fun fetchNowPalying(@Query("page")page :Int,@Query("api_key") apiKey: String): Observable<NowPlayingData>

    @GET("3/movie/{id}/similar")
    fun fetchRelated(@Path("id") id:String,@Query("api_key") apiKey: String): Observable<NowPlayingData>
}