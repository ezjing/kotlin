package com.busanit.ch09_resource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.busanit.ch09_resource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textView
        textView.text = getString(R.string.txt_data2)
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.txt_color, null))
        textView.setBackgroundResource(R.color.txt_bg_color)
        textView.setTextSize(resources.getDimension(R.dimen.txt_size))

        // 플랫폼 리소스 사용
        val imageView = binding.imageView
        imageView.setImageDrawable(ResourcesCompat.getDrawable(
            resources, android.R.drawable.alert_dark_frame, null))
        textView.text = getString(android.R.string.emptyPhoneNumber)
    }
}











