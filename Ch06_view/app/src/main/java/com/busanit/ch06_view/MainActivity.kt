package com.busanit.ch06_view

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.busanit.ch06_view.databinding.ActivityMainBinding

// 기능적인것 설정
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)   // 뷰 바인딩 객체 획득 (build.gradle에서 먼저 설정)
        setContentView(binding.root)  // 뷰 바인딩으로 액티비티 화면 출력
        // 2. activity_main.xml에서 화면 구성 (코드가 간단해짐 xml에서는 각종 설정, kt 에서는 화면 출력)
        // 4. 뷰 바인딩 기법 사용 binding.id명
        val btn1 = binding.btn1
        val btn2 = binding.btn2
        val imgView = binding.imgView

        // 3. 버튼 기능 구현(경로타고 id 로 가져옴)
//        val btn1 : Button = findViewById(R.id.btn1)
//        val btn2 = findViewById<Button>(R.id.btn2)
//        val imgView : ImageView = findViewById(R.id.imgView)

        btn1.setOnClickListener {
            imgView.visibility = View.VISIBLE
        }
        btn2.setOnClickListener {
            imgView.visibility = View.INVISIBLE
        }

        // TextView에 marquee 효과 설정
        val marqueeText = binding.marqueeText
        marqueeText.isSelected = true

        // 1. 소스코드에서 화면 구성
        // 이름 문자열 출력 TextView 생성
//        val name = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "Lake Louise"
//        }
//        // apply : 해당 객체의 속성을 바로 설정
//        // name.typeface = ....    name.text = ....
//
//        // 사진 부여줄 이미지 뷰 생성
//        val image = ImageView(this).also{
//            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lake_1))   // it키워드 사용, this 뒤에 jpg 파일 주소
//            it.adjustViewBounds = true  // 축소할 때 비율에 맞게 가로, 세로 축소
//        }
//
//        // 주소 문자열 출력 TextView 생성
//        val address = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "Lake Louise, AB, Canada"
//        }
//        // 뷰를 배치할 리니어레이아웃 생성
//        val layout = LinearLayout(this).apply {
//            orientation = LinearLayout.VERTICAL // 세로배치
//            gravity = Gravity.CENTER    // 가운데정렬
//            addView(name, WRAP_CONTENT, WRAP_CONTENT) // wrap_content : 컨텐츠 자기자신의 크기만큼 채움, 가로 세로 순
//            addView(image, WRAP_CONTENT, WRAP_CONTENT)
//            addView(address, WRAP_CONTENT, WRAP_CONTENT)
//        }
//
//        // 리니어 레이아웃을 화면에 출력
//        setContentView(layout)
    }
}