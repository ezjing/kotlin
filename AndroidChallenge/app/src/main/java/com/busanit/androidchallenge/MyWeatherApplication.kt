package com.busanit.androidchallenge

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyWeatherApplication: Application() {

    companion object {
        val serviceKey = "N9jLaOQ6gZTdZPEQTWZiXorU1DsZc17Mk8cuhrPYOFIKhTyF5KVf73ctXS9KQqobH8ZhjcyuqUxisbewnnOU7g=="
        val BASE_URL = "https://apis.data.go.kr/"
//        val USER_AGENT = "Mozilla/5.0 * (Windows NT 10.0; Win64; x64) AppleWebKit/537.36(KHTML, like Gecko)" +
//                "Chrome/60.0.3112.113 Safari/537.36"
//        val pageNo = " "
//        val numOfRows = " "
        val dataType = "JSON"
        val baseDate = "20230627"  // 현재시간 넣을것
        val baseTime = "0500"
        val nx = 35
        val ny = 129

//        val nx = 55
//        val ny = 127

        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit = Retrofit.Builder() // 레트로핏 생성
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        var networkService: INetworkService = retrofit.create(INetworkService :: class.java)
    }
}