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

class VolleyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVolleyBinding.inflate(inflater, container, false)
        val volleyRecyclerView = binding.volleyRecyclerView
        // url 정리
        val url = MyApplication.BASE_URL + "/v2/everything?q=${MyApplication.Query}&apiKey=" +
                "${MyApplication.API_KEY}&page=1&pageSize=10"
        // RequestQueue 생성
        val queue = Volley.newRequestQueue(activity)

        val jsonRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener<JSONObject> {response ->
                val jsonArray = response.getJSONArray("articles")   // 반복되는 태그는 array
                val itemList = mutableListOf<ItemModel>()
                for (i in 0 until jsonArray.length()) {
                    ItemModel().run {
                        val article = jsonArray.getJSONObject(i)    // 하나뿐인 태그는 object
                        author = article.getString("author")
                        title = article.getString("title")
                        description = article.getString("description")
                        urlToImage = article.getString("urlToImage")
                        publishedAt = article.getString(("publishedAt"))
                        itemList.add(this)  // this 는 ItemModel을 말함.
                    }
                }
                volleyRecyclerView.layoutManager = LinearLayoutManager(activity)
                volleyRecyclerView.adapter = MyAdapter(activity as Context, itemList)
            }, Response.ErrorListener {error ->
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
        queue.add(jsonRequest)

        return binding.root
    }
}