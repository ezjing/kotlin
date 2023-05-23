package com.busanit.ch11_jetpacklast

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch11_jetpacklast.databinding.FragmentOneBinding
import com.busanit.ch11_jetpacklast.databinding.ItemRecyclerviewBinding

// 리사이클러뷰 관련 클래스 모두 만들기
// 뷰홀더
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

// 어댑터
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter <RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }
}

// ItemDecoration : 아이템 꾸미기
class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val width = parent.width
        val height = parent.height

        val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight

        val left = width / 2  - drWidth?.div(2) as Int
        val top = height / 2 - drHeight?.div(2) as Int

        c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),
        left.toFloat(), top.toFloat(), null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1
        if(index %  3 == 0) outRect.set(10, 10, 10, 60)
        else outRect.set(10, 10, 10, 0)
        view.setBackgroundColor(Color.parseColor("#28a0ff"))
        ViewCompat.setElevation(view, 20.0f)
    }
}

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        // data 생성, layoutManager, adapter 만들어서 연결
        val datas = mutableListOf<String>()
        for (i in 1.. 20) {
            datas.add("item $i")
        }
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(activity)   // 프래그먼트이기에 액티비티 정보를 가져와 전달
        recyclerView.layoutManager = layoutManager

        val adapter = MyAdapter(datas)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(MyDecoration(activity as Context))


        return binding.root
    }
}