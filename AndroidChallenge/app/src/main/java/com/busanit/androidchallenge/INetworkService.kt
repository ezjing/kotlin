package com.busanit.androidchallenge

import com.busanit.androidchallenge.model.WeatherItemModel
import com.busanit.androidchallenge.model.WeatherItemsModel
import com.busanit.androidchallenge.model.WeatherModel
import com.busanit.androidchallenge.model.WeatherResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URLDecoder

interface INetworkService {
    @GET("1360000/VilageFcstInfoService_2.0/getVilageFcst")
//    @GET
    fun getWeatherList( // pageNo, numOfRows, dataType, base_date, base_time, nx, ny
        @Query("serviceKey") serviceKey: String?,
        @Query("pageNo") pageNo: Int?,
        @Query("numOfRows") numOfRows: Int?,
        @Query("dataType") dataType: String?,
        @Query("base_date") baseDate: String?,
        @Query("base_time") baseTime: String?,
        @Query("nx") nx: Int?,
        @Query("ny") ny: Int?
    ): Call<WeatherModel>
}