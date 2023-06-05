package com.busanit.ch13_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.busanit.ch13_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var cnt = 0
    lateinit var textCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 액티비티 이동, 인텐트 사용 기본
        val goDetail = binding.goDetail
        val intent: Intent = Intent(this, DetailActivity::class.java)   // 목적지 전달
//        goDetail.setOnClickListener {
//            startActivity(intent)
//        }

        // 2. 데이터 전달
        intent.putExtra("data1", "hello")
        intent.putExtra("data2", 10)
//        goDetail.setOnClickListener {
//            startActivity(intent)
//        }

        // 3. 결과를 가지고 돌아오는 경우 ActivityResultLauncher 사용
        val editResult = binding.editResult
        // ActivityResultLauncher 객체 생성
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val resultData = it.data?.getStringExtra("result")
            editResult.setText(resultData)
        }
        goDetail.setOnClickListener {
            requestLauncher.launch(intent)
        }

        // 4. 생명주기 확인(myLog 순서 확인)
        Log.d("myLog", "onCreate()...")
        val btnCount = binding.btnCount
        textCount = binding.textCount
        btnCount.setOnClickListener {
            cnt += 1
            textCount.text = cnt.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 화면 회전 대응, 데이터 저장
        Log.d("myLog", "onSaveInstanceState()...")
        outState.putInt("cnt", cnt)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 번들 객체에 저장된 데이터 복원
        Log.d("myLog", "onRestoreInstanceState()...")
        cnt = savedInstanceState.getInt("cnt")
        textCount.text=cnt.toString()
    }

    override fun onStart() {
        Log.d("myLog", "onStart()...")
        super.onStart()
    }

    // 앱 사용중 전화같은게 올때
    override fun onResume() {
        Log.d("myLog", "onResume()...")
        super.onResume()
    }

    override fun onPause() {
        Log.d("myLog", "onPause()...")
        super.onPause()
    }

    // 앱 종료시
    override fun onStop() {
        Log.d("myLog", "onStop()...")
        super.onStop()
    }

    // 앱이 메모리에서 지워질 때
    override fun onDestroy() {
        Log.d("myLog", "onDestroy()...")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("myLog", "onRestart()...")
        super.onRestart()
    }
}