package com.busanit.ch18_retrofit

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.busanit.ch18_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 4. Retrofit 객체를 만들어서 제공할 클래스 구현
// 컴패니언 클래스 : 이후 클래스명으로 함수에 접근
class MyApplication: Application() {
    companion object {
        var retrofit: Retrofit
        var networkService : INetworkService
        init {
            retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")  // 기본 주소
                .addConverterFactory(GsonConverterFactory.create()) // GsonConverter 이용하여 데이터 파싱
                .build()
            networkService = retrofit.create(INetworkService::class.java)
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getQuery.setOnClickListener {     // 바인딩할때 get_query 이렇게되있으면 카멜명명법으로 바뀜
            val userListCall = MyApplication.networkService.doGetUserList("1")  // networkService에서 넘어오는 페이지 데이터 타입 String으로 지정해둬서 문자열로 받아옴
            userListCall.enqueue(object : Callback<UserListModel> {
                override fun onResponse(    // 통신이 성공했을 때 호출
                    call: Call<UserListModel>,
                    response: Response<UserListModel>
                ) {
                    val userList = response.body()  // 1페이지의 내용 출력 (id 1~6 출력)
                    Log.d("myLog", "userList : $userList")
                }

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {   // 통신이 실패했을 때 호출
                    Log.d("myLog", "통신 실패")
                }
            })
        }

        binding.getPath.setOnClickListener {
            val userModel = MyApplication.networkService.doGetUser(1)   // id 값 Int 로 했기 때문에 정수로 받아옴
            userModel.enqueue(object : Callback<ResponseData> { // Call 데이터 타입 ResponseData로 지정하여 data의 자식 태그들의 값을 얻어와 출력할수 있도록 함(UserModel 쓰면 data 태그를 거치지 않고 바로 자식 태그의 데이터를 얻어 오려 해서 값이 입력 안됨)
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val user = response.body()  // id = 1인 유저의 정보 출력
                    val id = user?.userModel?.id
                    Log.d("myLog", "user : $user")
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("myLog", "통신 실패 : ${t.message}")
                }

            })
        }

        // glide 사용하기
        binding.glideTest.setOnClickListener {
            Glide.with(this)
                .load("https://reqres.in/img/faces/1-image.jpg")    // 이미지 url 주소 입력
                .override(500, 500) // 크기 조절(생략 가능)
                .placeholder(R.mipmap.ic_launcher_round)    // 이미지 로딩 시 최초에 보여줄 이미지(이미지 경로 지정하면 됨)(생략 가능)
                .error(R.drawable.todo) // 에러 발생 시 보여줄 이미지(생략 가능)
                .into(binding.glideImage)   // 이미지 출력할 뷰 전달
        }
    }
}
