package com.busanit.ch18_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.busanit.ch18_news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)    // 툴바 세팅

        // 3. volley fragment 초기화면 구성
        volleyFragment = VolleyFragment()   // 발리 프래그먼트 선언
        retrofitFragment  = RetrofitFragment()  // 레트로핏 프래그먼트 선언
        
        supportFragmentManager.beginTransaction().replace(R.id.activity_content, volleyFragment).commit()   // activity_content에 발리 프래그먼트 출력
        supportActionBar?.title = "Volley Test" // 초기화면 발리 (액션바)제목 지정(?는 null이 올수도 있기 때문에 없으면 title에 에러 발생)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)    // menu 출력
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_volley && mode != "volley") { // 발리 버튼을 눌렀을때 모드가 발리가 아니면
            supportFragmentManager.beginTransaction().replace(R.id.activity_content, volleyFragment).commit()   // activity_content에 발리 프래그먼트 출력
            mode = "volley" // 발리 프래그먼트로 모드 지정
            supportActionBar?.title = "Volley test" // 발리 (액션바)제목을 가져온다
        }
        else if (item.itemId === R.id.menu_retrofit && mode != "retrofit") {    // 레트로핏 버튼을 눌렀을때 모드가 레트로핏이 아니면
            supportFragmentManager.beginTransaction().replace(R.id.activity_content, retrofitFragment).commit()   // activity_content에 레트로핏 프래그먼트 출력
            mode = "retrofit"   // 레트로핏 프래그먼트로 모드 지정
            supportActionBar?.title = "Retrofit Test"   // 레트로핏 (액션바)제목을 가져온다
        }
        return super.onOptionsItemSelected(item)
    }
}