package com.busanit.ch11_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.busanit.ch11_viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. RecyclerView.Adapter를 이용한 뷰페이저 만들기
//        val viewPager = binding.viewPager
//        val datas = mutableListOf<String>()
//        for (i in 1 .. 3) {
//            datas.add("Item $i")
//        }
//        viewPager.adapter = MyPagerAdapter(datas)
        
        // 2. FragmentStateAdapter를 이용한 뷰페이저 만들기
        // 2-1. 프래그먼트 세개 생성
        // 2-2. MyFragmentPagerAdapter 생성
        // 2-3. 어댑터 생성하고, 연결하기
        val viewPager = binding.viewPager
        viewPager.adapter = MyFragmentPagerAdapter(this)
        // 2-4. 방향 변경
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL // 방향 지정
    }
}