package com.busanit.androidchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.busanit.androidchallenge.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

// 주제는 날씨 + 뉴스 인걸로..
class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyFragmentPagerAdapter    // 뷰페이저 사용을 위한 어뎁터

    lateinit var weatherFragment: WeatherFragment
    lateinit var newsFragment: NewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)

        weatherFragment = WeatherFragment()
        newsFragment = NewsFragment()

        supportFragmentManager.beginTransaction().replace(R.id.activity_content, weatherFragment).commit()
        supportActionBar?.title = "날뉴"

        val tabLayout = binding.tabs

        adapter = MyFragmentPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "날씨"
            }
            else if (position == 1) {
                tab.text = "뉴스"
            }
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, "검색 내용 : ${query}", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}