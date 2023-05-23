package com.busanit.ch11_recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch11_recyclerview.databinding.ItemMainBinding

// 2-1. 뷰홀더 준비
class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

// 2-2. 어댑터 준비
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    // 뷰홀더 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    // 항목 개수 리턴
    override fun getItemCount(): Int {
        return datas.size
    }
    // 각 뷰홀더에 데이터 연결
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(
            "myLog",
            "onBindViewHolder : $position"
        )  // 로딩되는 item 인덱스 출력 (인덱스라서 item20 = 19 이라고 보면됨)
        val binding = (holder as MyViewHolder).binding
        // 뷰에 데이터 출력
        binding.itemData.text = datas[position]
        // 뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener {
            Log.d("myLog", "item root click : $position")   // 클릭시 해당 item의 인덱스 출력 (item3 = 2)
        }
    }

    // 4. 데이터 추가, 제거
    fun addItem() {
        datas.add("새로운 아이템 ${datas.size + 1}")  // 마지막 번호 뒤부터 추가
        notifyDataSetChanged() // 데이터 변경을 어댑터에 알려줌, 갱신
    }
    fun removeItem() {
        if(datas.size != 0) {
            datas.removeAt(datas.size - 1)  // 마지막 번호부터 삭제
            notifyDataSetChanged()
        }
        else {
            Toast.makeText(context, "삭제할 항목이 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
