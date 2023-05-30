package com.busanit.ch12_tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.ch12_tab.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyFragmentPagerAdapter    // 뷰페이저 사용을 위한 어뎁터

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
        val fragments: List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs
        // 소스코드에서 탭 생성(탭 객체 생성)(소스코드에서 잘 디자인 하지 않음)
//        val tab1: TabLayout.Tab = tabLayout.newTab()
//        tab1.text = "Tab1"
//        tabLayout.addTab(tab1)
//
//        val tab2: TabLayout.Tab = tabLayout.newTab()
//        tab2.text = "Tab2"
//        tabLayout.addTab(tab2)
//
//        val tab3: TabLayout.Tab = tabLayout.newTab()
//        tab3.text = "Tab3"
//        tabLayout.addTab(tab3)
//        tabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val transaction = supportFragmentManager.beginTransaction()
//                when(tab?.text){
//                    "Tab1" -> transaction.replace(R.id.tabContent, OneFragment())
//                    "Tab2" -> transaction.replace(R.id.tabContent, TwoFragment())
//                    "Tab3" -> transaction.replace(R.id.tabContent, ThreeFragment())
//                }
//                transaction.commit()
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//        })
        
        // 3. 뷰페이저 연동
        adapter = MyFragmentPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        // 탭과 뷰페이저 연결(TabLayoutMediator)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${position + 1}" // position은 인덱스라 0부터 시작
        }.attach()
    }
}