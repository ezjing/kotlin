package com.busanit.ch18_news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.busanit.ch18_news.databinding.ItemMainBinding
import com.busanit.ch18_news.model.ItemModel

class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) // 뷰홀더 클래스(item_main을 바인딩해옴)

class MyAdapter(val context: Context, val datas: MutableList<ItemModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() { // 어뎁터 클래스(Context와 데이터가 담긴 List 매개변수로 받아옴)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {    // 뷰 홀더 생성
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {  // 아이템 개수
        return datas?.size ?: 0 // 삼항연산자 true 일때 실행하는 부분을 생략한 것(사이즈에 아무거도 없으면 0 출력 하란 뜻)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val model = datas!![position]   // datas에 담겨온 ItemModel 객체를 변수에 할당
        binding.itemTitle.text = model.title    // 각 뷰마다 바인딩
        binding.itemDesc.text = model.description
        binding.itemTime.text = "${model.author} At ${model.publishedAt}"
        Glide.with(context)
            .load(model.urlToImage)
            .into(binding.itemImage)
    }
}