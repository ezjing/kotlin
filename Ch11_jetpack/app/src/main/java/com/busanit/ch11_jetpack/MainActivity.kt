package com.busanit.ch11_jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.busanit.ch11_jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 6. 툴바 생성
//        val toolbar = binding.toolbar // 방법 1
//        setSupportActionBar(toolbar)
        setSupportActionBar(binding.toolbar)    // 방법 2

        // 2. Two Activity로 이동
        val btnGo = binding.btnGo
        btnGo.setOnClickListener {
            val intent : Intent = Intent(this, TwoActivity::class.java)
            startActivity(intent)
        }

        // 8. 프래그먼트 소스코드에서 제어
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()
    }

    // 3. 소스코드에서 메뉴 생성
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {    // 메뉴 생성
//        val MenuItem1: MenuItem? = menu?.add(0, 0, 0, "menu1")
//        val MenuItem2: MenuItem? = menu?.add(0, 1, 0, "menu2")
//        return super.onCreateOptionsMenu(menu)
//    }

    // 4. 리소스(menu_main.xml)로 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)

        // 5. SearchView(androidx) 기능 구현
        val menuItem = menu?.findItem(R.id.menu3)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Log.d("myLog", "검색 이벤트 발생 : ${query}")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                Log.d("myLog", "키보드 입력값 실시간 출력 : ${newText}")
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 선택 시 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {   // 메뉴 클릭시 발생 이벤트 설정
        0 -> {
            Log.d("myLog", "menu1 click....")
            true
        }
        1 -> {
            Log.d("myLog", "menu2 click....")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}