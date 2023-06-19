package com.busanit.ch18_retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface INetworkService { // 사용할 함수 선언만 규정
    // getQuery
    @GET("api/users")   // https://reqres.in/ 페이지의 LIST USERS (api 앞에 '/' 붙이면 안됨. 메인액티비티 기본주소에 넣음)
    fun doGetUserList(@Query("page") page:String) : Call<UserListModel> // @Query가 있어서 ? 안써도 됨
    // 실제 url 주소 : https://reqres.in/api/users?page=넘어온 page 값

    // getPath
    @GET("api/users/{id}")  // https://reqres.in/ 페이지의 SINGLE USER
    fun doGetUser(@Path("id") userId: Int) : Call<ResponseData> // 쿼리스트링 없는 주소. @Path가 있어서 / 안써도 됨
    // 실제 url 주소 : https://reqres.in/api/users/넘어온 id 값
}