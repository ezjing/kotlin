package com.busanit.ch18_retrofit

import com.google.gson.annotations.SerializedName


data class UserListModel(
    var page: String,

    @SerializedName("per_page") // json 데이터의 키값과 변수명이 다를 때
    var perPage: String,
    var total: String,

    @SerializedName("total_pages")
    var totalPages: String,
    var data: List<UserModel>?
)

