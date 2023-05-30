package com.busanit.ch12_materiallast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.ch12_materiallast.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

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

        // 1. 툴바, 토글 버튼 적용
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val drawer = binding.drawer
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        
        // 3. 프래그먼트 생성, 뷰페이저 탭 연동
        // MyFragmentPagerAdapter 만들기
        val viewPager = binding.viewPager
        val adapter = MyFragmentPagerAdapter(this)
        viewPager.adapter = adapter

        val tabs = binding.tabs
        TabLayoutMediator(tabs, viewPager) {
            tab, position ->
            tab.text = "Tab${position + 1}"
        }.attach()

    }

    // 1관련, 토글 버튼 클릭시 drawer 화면 나오게 하기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected((item))) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}