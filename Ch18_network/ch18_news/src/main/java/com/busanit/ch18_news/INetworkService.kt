package com.busanit.ch18_news

import com.busanit.ch18_news.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface INetworkService { // 레트로핏을 사용할때 쓰는 인터페이스
    @GET("/v2/everything")
    fun getList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int
    ): Call<PageListModel>  // 쿼리스트링으로 받아올 4가지 항목
}