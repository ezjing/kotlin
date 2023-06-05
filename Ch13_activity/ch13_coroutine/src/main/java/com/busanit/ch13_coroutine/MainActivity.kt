package com.busanit.ch13_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.busanit.ch13_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnGo = binding.btnGo
        val resultView = binding.resultView

//        btnGo.setOnClickListener {    // EditText 터치하면 ANR 발생
//            var sum = 0L
//            var time = measureTimeMillis {  // {} 실행하는데 소요된 시간 리턴
//                for (i in 1..6_000_000_000) {
//                    sum += i
//                }
//            }
//            Log.d("myLog", "time : $time")
//            resultView.text = "sum : $sum"
//        }

        // 대안 1. 스레드-핸들러 구조 활용
//        btnGo.setOnClickListener {
//            val handler = object : Handler() {
//                override fun handleMessage(msg: Message) {  // 메인스레드에서 UI 변경
//                    super.handleMessage(msg)
//                    resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//            thread {    // 별도 스레드 생성(시간이 걸리는 작업 특히 네트워크 작업은 별도 스레드 생성해야 ANR문제 발생 안함)
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..6_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1 = sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("myLog", "time : $time")
//            }
//        }
        
        // 대안2. 코루틴 활용(build.gradle에 dependency 추가)
        btnGo.setOnClickListener {
            val channel = Channel<Int>()
            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())   // 백그라운드에서 오래 걸리는 작업
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for(i in 1.. 6_000_000_000){
                        sum += 1
                    }
                }
                Log.d("myLog", "time : $time")
                channel.send(sum.toInt())
            }

            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    resultView.text ="sum : $it"
                }
            }
        }
    }
}