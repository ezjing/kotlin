package com.busanit.ch08_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.CompoundButton
import androidx.activity.OnBackPressedCallback
import com.busanit.ch08_event.databinding.ActivityMainBinding

// 4-3. 이벤트 핸들러 클래스 만들기
class MyEventHandler : CompoundButton.OnCheckedChangeListener{
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("myLog", "체크박스 클릭")
    }
}

// 4-2. compoundbutton.OnCheckedChangeListener 상속
//class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
class MainActivity : AppCompatActivity() {
    // 3-1. 뒤로가기 구현(onBackPressedCallback)
    val callback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            Log.d("myLog", "Back 버튼을 눌렀네요.")
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkBox = binding.checkBox
        // 4-1. onCheckedChangeListener (자바 방식)
        // 이벤트 소스 : 이벤트가 발생한 객체 (checkBox)
        // 이벤트 핸들러 : 이벤트 발생 시 실행할 로직이 구현된 객체( object)
        // 리스너 : 이벤트 소스와 핸들러를 연결( setOnCheckedChangeListener)

//        checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
//            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                Log.d("myLog", "체크박스 클릭")
//            }
//        })

        // 4-4. SAM 기법 : override 메서드가 하나일 때, 일반적 방법
        // 함수명 뒤에 () 없이 바로{}
        checkBox.setOnCheckedChangeListener{ compoundButton, b ->
            Log.d("myLog", "체크박스 클릭 : $b")
        }


        // 4-2. setOnChecked~ 두번째 방법, 액티비티에서 인터페이스 구현
//        checkBox.setOnCheckedChangeListener(this)

        // 4-3. 이벤트 핸들러를 별도 클래스로 작성
       // checkBox.setOnCheckedChangeListener(MyEventHandler())

        // 3-1. onBackPressedCallback 관련
        this.onBackPressedDispatcher.addCallback(this, callback)

        //5-1. 클릭 이벤트 자바 방식
        val button = binding.button
//        button.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                Log.d("myLog", "버튼 클릭")
//            }
//        })

        // 5-2. 클릭 이벤트 SAM 기법 사용
        button.setOnClickListener {
            Log.d("myLog", "버튼 클릭")
        }

        // 5-3. 롱클릭 이벤트
        button.setOnLongClickListener {
            Log.d("myLog", "버튼 롱~~~ 클릭")
            true// false일 경우, 클릭 후 뗄떼 onClick 이벤트 발생
        }
    }
    // 4-2. 관련
//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Log.d("myLog", "체크박스 클릭")
//    }

//    3. onBackPressed
//    override fun onBackPressed() {
//    Log.d("myLog", "Back Button을 눌렀네요.")
//        super.onBackPressed()
//    }

//    2. Key 이벤트
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
    when(keyCode){
        KeyEvent.KEYCODE_BACK -> Log.d("myLog", "Back Button을 눌렀습니다.")
        KeyEvent.KEYCODE_VOLUME_UP -> Log.d("myLog", "Volume Up키를 눌렀습니다.")
    }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_BACK -> Log.d("myLog", "Back Button, Key Up event.")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("myLog", "Volume Up, Key Up event.")
        }
        return super.onKeyUp(keyCode, event)
    }

//    1. touch 이벤트
    override fun onTouchEvent(event: MotionEvent?): Boolean {
    when(event?.action){
        MotionEvent.ACTION_DOWN -> {
            Log.d("myLog", "Touch down event : ${event.x}, rawX : ${event.rawX}")
        }
        // x, rawX : x는 터치이벤트가 걸린 뷰에서의 좌표, rawX는 전체화면에서의 좌표
        MotionEvent.ACTION_UP -> {
            Log.d("myLog", "Touch up event")
        }
        MotionEvent.ACTION_MOVE -> {
            Log.d("myLog", "Touch move event : ${event.x}")
        }
    }
        return super.onTouchEvent(event)
    }
}













