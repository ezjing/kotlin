package com.busanit.ch11_jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.busanit.ch11_jetpack.databinding.ActivityTwoBinding


/* app 우클릭 new - activity - empty views activity - Activity Name에 TwoActivity 입력 및 생성 */
class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("muLog", "onSupportNavigateUp")
        onBackPressed() // 이전으로 가기
        return super.onSupportNavigateUp()
    }
}