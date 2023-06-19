package com.busanit.ch18_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.busanit.ch18_network.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.StringBuilder
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.button
        textView = binding.textView
        button.setOnClickListener {
            makeRequest()
        }
        queue = Volley.newRequestQueue(this)    // Volley는 HTTP 통신을 쉽게 구현하도록 해주는 공개 라이브러리   // queue는 내용등록하면 혼자 배열 만들고 스레드 만들고 해주는 애
    }

    private fun makeRequest() {
        // targetDt 구하기(어제 날짜)
        val cal = Calendar.getInstance()    // 날짜 가져오기
        cal.add(Calendar.DATE, -1)  //연도를 바꾸고 싶으면 YEAR.. 이런식으로 바꾸고 싶은거 쓰면 됨, 하루전꺼를 보여주기 때문에 -1
        val df = SimpleDateFormat("yyyyMMdd")   // 날짜 포맷 정하기
        textView.append("Daily Box Office : ${df.format(cal.time)}\n\n")    // 위 설정한 날짜 출력
        
        // 1. StringRequest로 xml 문서 보여주기 (2. 할때 전부 주석처리)
//        val str = StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml")   // xml 주소 입력
//        str.append("?" + URLEncoder.encode("key", "UTF-8") + "=7c5a1717bd4dfd6fc6f90c2590ee8d6f")   // 영화진흥위원회 api에서 발급 받은 인증 key 입력
//        str.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=${df.format(cal.time)}")    // cal.time 으로 시간값 입력
//        Log.d("myLog", "Url : $str")
//
//        val stringRequest = object : StringRequest(
//            Request.Method.GET,  // 통신방식 : GET
//            str.toString(), // 주소 문자열로 변경. 이 과정에서 주소가 xml파일로 파싱 되는거로 보임
//            Response.Listener<String> { // 통신 성공 시(응답 왔을때) 해야할 일
//                textView.append(it) // textView에 문자열로된 주소(it) 추가.. textView 영역에 xml 파일 내용 출력
//            }, Response.ErrorListener {error ->   // 통신 실패 시(에러 발생시) 해야할 일
//                Log.d("myLog", "error : ${error.message}")
//            })  {
//            override fun getParams(): MutableMap<String, String>? {
//                return super.getParams()
//            }
//        }
//        stringRequest.setShouldCache(false)
//        queue.add(stringRequest)
        
        // 2. JsonObjectRequest로 데이터 파싱하기 (리사이클러 뷰같은거 만들때는 json이 더 편하다 데이터가 정리돼서 나옴)
        val str = StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")   // json 주소 입력
        str.append("?" + URLEncoder.encode("key", "UTF-8") + "=7c5a1717bd4dfd6fc6f90c2590ee8d6f")   // 영화진흥위원회 api에서 발급 받은 인증 key 입력
        str.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=${df.format(cal.time)}")    // cal.time 으로 시간값 입력
        Log.d("myLog", "Url : $str")

        val jsonRequest = object : JsonObjectRequest(
            Request.Method.GET, str.toString(), null, Response.Listener<JSONObject> {response ->    // 정상 통신 시
                val jsonObject = response.getJSONObject("boxOfficeResult")  // json의 최상위 태그부터 차례로 하위 태그로 내려감
                val jsonArray = jsonObject.getJSONArray("dailyBoxOfficeList")
                for (i in 0 until jsonArray.length()) { // 10개 있으니 0 ~ 9 까지 반복 시킨다는 뜻
                    val movie = jsonArray.getJSONObject(i)   // JSONObject : 반복없이 하나의 태그인 것, JSONObject : 반복되는 여러개의 태그인 것
                    val rank = movie.getString("rank")  // 각 컬럼 정보 입력
                    val title = movie.getString("movieNm")
                    val audiAcc = movie.getString("audiAcc")
                    textView.append(rank + ". " + title + ", " + audiAcc + "명 관람\n")    // 뷰 영역에 출력
                }
            }, Response.ErrorListener {error ->     // 에러 발생시
                Log.d("myLog", "통신 실패 : $error")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }
        }
        queue.add(jsonRequest)
    }
}