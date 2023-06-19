package com.busanit.ch18_retrofit

import com.google.gson.annotations.SerializedName

data class UserModel (
    var id: Int,
    var first_name: String,
    var last_name: String,
    var avatar: String,
    var email: String
)

data class ResponseData (   // UserModel의 필드가 속해있는 data 만들어 줘야함 없으면 아무런 값이 들어오지 않음
    @SerializedName("data")
    var userModel: UserModel
)