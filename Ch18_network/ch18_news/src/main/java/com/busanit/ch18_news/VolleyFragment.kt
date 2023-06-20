package com.busanit.ch18_news

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.busanit.ch18_news.databinding.FragmentVolleyBinding
import com.busanit.ch18_news.model.ItemModel
import com.google.gson.JsonObject
import org.json.JSONObject

class VolleyFragment : Fragment() { // 현 프래그먼트에선 json 방식 사용
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVolleyBinding.inflate(inflater, container, false)
        val volleyRecyclerView = binding.volleyRecyclerView
        // url 정리(기본 URL 에 검색어와 key 입력)
        val url = MyApplication.BASE_URL + "/v2/everything?q=${MyApplication.Query}&apiKey=" +
                "${MyApplication.API_KEY}&page=1&pageSize=10"
        // RequestQueue 생성(RequestQueue : 서버에 요청을 보내는 역할, 여기서 activity는 프래그먼트를 말하며 해당 프래그먼트를 서버에 요청 보냄)
        val queue = Volley.newRequestQueue(activity)

        val jsonRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener<JSONObject> {response ->   // 통신 성공 ㅅ
                val jsonArray = response.getJSONArray("articles")   // 반복되는 태그는 array(그냥 배열 받아 오는 것)
                val itemList = mutableListOf<ItemModel>()   // List 받아오기
                for (i in 0 until jsonArray.length()) { // 배열의 크기만큼 반복
                    ItemModel().run {
                        val article = jsonArray.getJSONObject(i)    // 하나뿐인 태그는 object(각 컬럼 가져오기)
                        author = article.getString("author")
                        title = article.getString("title")
                        description = article.getString("description")
                        urlToImage = article.getString("urlToImage")
                        publishedAt = article.getString(("publishedAt"))
                        itemList.add(this)  // this 는 ItemModel을 말함.(List에 배열을 추가)
                    }
                }
                volleyRecyclerView.layoutManager = LinearLayoutManager(activity)    // 리사이클러뷰 매니저
                volleyRecyclerView.adapter = MyAdapter(activity as Context, itemList)   // 리사이클러뷰 어댑터
            }, Response.ErrorListener {error -> // 통신 실패시
                Log.d("myLog", "통신 실패 : $error")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>(
                    "User-agent" to MyApplication.USER_AGENT
                )
                return map
            }
        }
        queue.add(jsonRequest)  // queue에 json으로 받아온 데이터 추가

        return binding.root
    }
}