package com.busanit.ch13_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.busanit.ch13_activity.databinding.ActivityDetailBinding

// app 우클릭 - new - activity - empty views activity
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. 인텐트 수신, 수신된 데이터 표시하기
        val textData = binding.textData
        val intent = intent
        val data1 = intent.getStringExtra("data1")
        var data2 = intent.getIntExtra("data2", 0)  // 0은 data2에 데이터가 없을때 표기되는 초기값
        val str = "전달된 데이터 : $data1, $data2"
        textData.setText(str)

        // 3. 응답 데이터 세팅
        data2 += 10
        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.putExtra("result", "$data1, $data2 from DetailActivity")
        setResult(RESULT_OK, resultIntent)
        finish()    // DetailActivity 종료
    }
}