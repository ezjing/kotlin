package com.busanit.androidchallenge

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
import com.busanit.androidchallenge.databinding.FragmentNewsBinding
import com.busanit.androidchallenge.model.NewsArticlesModel
import com.google.gson.JsonObject
import org.json.JSONObject

class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        val newsRecyclerView = binding.newsRecyclerView
        val url = MyNewsApplication.BASE_URL + "v2/everything?q=${MyNewsApplication.Query}&apiKey=" +
                "${MyNewsApplication.API_KEY}&page=1&pageSize=10"
        val queue = Volley.newRequestQueue(activity)

        val jsonRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener<JSONObject> { response ->
                val jsonArray = response.getJSONArray("articles")
                val itemList = mutableListOf<NewsArticlesModel>()
                for (i in 0 until jsonArray.length()) {
                    NewsArticlesModel().run {
                        val article = jsonArray.getJSONObject(i)
                        author = article.getString("author")
                        title = article.getString("title")
                        description = article.getString("description")
                        urlToImage = article.getString("urlToImage")
                        publishedAt = article.getString(("publishedAt"))
                        itemList.add(this)
                    }
                }
                newsRecyclerView.layoutManager = LinearLayoutManager(activity)    // 리사이클러뷰 매니저
                newsRecyclerView.adapter = MyNewsAdapter(activity as Context, itemList)   // 리사이클러뷰 어댑터
            }, Response.ErrorListener {error ->
                Log.d("myLog", "통신 실패 : $error")
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>(
                    "User-agent" to MyNewsApplication.USER_AGENT
                )
                return map
            }
        }
        queue.add(jsonRequest)  // queue에 json으로 받아온 데이터 추가

         return binding.root
    }
}