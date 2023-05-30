package com.busanit.ch12_materiallast

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_materiallast.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트를 바인딩 할땐 매개변수가 3개(메인액티비티는 매개변수 1개)
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView
        val datas = mutableListOf<String>()
        for (i in 1..20) {
            datas.add("리사이클러 아이템$i")
        }
        // MyAdapter 만들기
        val adapter = MyAdapter(datas)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(MyDecoration(activity as Context))

        return binding.root
    }
}