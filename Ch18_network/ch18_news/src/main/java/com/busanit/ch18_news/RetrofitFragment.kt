package com.busanit.ch18_news

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch18_news.databinding.FragmentRetrofitBinding
import com.busanit.ch18_news.model.PageListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        val retrofitRecyclerView = binding.retrofitRecyclerView

        val call: Call<PageListModel> = MyApplication.networkService.getList(
            MyApplication.Query, MyApplication.API_KEY, 1, 10
        )
        call.enqueue(object : Callback<PageListModel> { // 서버와 통신 실행, Callback으로 PageListModel 부르고 Response로 받아온다
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) { // 통신 성공 시
                if (response.isSuccessful) {
                    retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)  // 리사이클러뷰 매니저
                    retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.articles)    // 리사이클러뷰 어댑터
                }
            }

            override fun onFailure(call: Call<PageListModel>, t: Throwable) {   // 통신 실패 시
                Log.d("myLog", "통신 실패 : ${t.message}")
            }
        })

        return binding.root
    }
}