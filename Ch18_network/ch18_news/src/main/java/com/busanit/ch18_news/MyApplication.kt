package com.busanit.ch18_news

import android.app.Application
import android.app.DownloadManager.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    companion object {
        // volley 용
        val Query = "여행"    // 검색 내용
        val API_KEY = "4b717e9db87e491cb31646126be48af0"    // api 키
        val BASE_URL = "https://newsapi.org"    // 기본 주소
        val USER_AGENT = "Mozilla/5.0 * (Windows NT 10.0; Win64; x64) AppleWebKit/537.36(KHTML, like Gecko)" +
                "Chrome/60.0.3112.113 Safari/537.36"    // user agent는 HTTP 요청을 보내는 디바이스와 브라우저 등 사용자 소프트웨어의 식별 정보를 담고 있는 request header의 한 종류

        // retrofit 용 추가
        val retrofit: Retrofit = Retrofit.Builder() // 레트로핏 생성
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var networkService: INetworkService = retrofit.create(INetworkService :: class.java)    // INetworkService 연결
    }
}