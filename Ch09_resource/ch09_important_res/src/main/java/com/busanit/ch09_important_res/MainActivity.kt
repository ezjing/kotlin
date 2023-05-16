package com.busanit.ch09_important_res

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowMetrics
import com.busanit.ch09_important_res.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 화면 정보 가져오기
        var width = 0
        var height = 0

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            width = windowMetrics.bounds.width()
            height = windowMetrics.bounds.height()
        } else{
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            height = displayMetrics.heightPixels
        }
        Log.d("myLog", "width * height : $width * $height")
    }
}












