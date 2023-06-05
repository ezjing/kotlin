package com.busanit.ch14_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.busanit.ch14_receiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 2. 소스에서 동적으로 브로드캐스트리시버 생성, 등록, 해제
//        val receiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//
//            }
//
//        }   // 생성
//        val filter = IntentFilter("ACTION_RECEIVER")
//        registerReceiver(receiver, filter) // 등록
//        unregisterReceiver(receiver)    // 해제

        // 3. 화면 켬, 끔 수신
//        val receiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                when(intent?.action) {  // 앱 닫혀있어도 상관없이 로그 수신
//                    Intent.ACTION_SCREEN_ON -> Log.d("myLog", "Screen On...")
//                    Intent.ACTION_SCREEN_OFF -> Log.d("myLog", "Screen Off...")
//                }
//            }
//        }
//        val filter = IntentFilter(Intent.ACTION_SCREEN_ON).apply {
//            addAction(Intent.ACTION_SCREEN_OFF)
//        }
//        registerReceiver(receiver, filter) // 등록

        // 4. 배터리 상태 수신
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when(intent?.action) {
                    Intent.ACTION_BATTERY_OKAY -> Log.d("myLog", "ACTION_BATTERY_OKAY")
                    Intent.ACTION_BATTERY_LOW -> Log.d("myLog", "ACTION_BATTERY_LOW")
                    Intent.ACTION_BATTERY_CHANGED -> Log.d("myLog", "ACTION_BATTERY_CHANGED")
                    Intent.ACTION_POWER_CONNECTED -> Log.d("myLog", "ACTION_POWER_CONNECTED")
                    Intent.ACTION_POWER_DISCONNECTED -> Log.d("myLog", "ACTION_POWER_DISCONNECTED")
                }
            }
        }
        val filter = IntentFilter(Intent.ACTION_BATTERY_LOW).apply {
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(receiver, filter)  // 등록

        // 5. 필요 시점에 배터리 정보 가져오기
        val btnBat = binding.btnBat
        btnBat.setOnClickListener {
            val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = registerReceiver(null, intentFilter)

            val status = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            if(status == BatteryManager.BATTERY_STATUS_CHARGING) {
                val chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                when(chargePlug) {
                    BatteryManager.BATTERY_PLUGGED_USB -> Log.d("myLog", "usb charge")
                    BatteryManager.BATTERY_PLUGGED_AC -> Log.d("myLog", "ac charge")
                }
            }
            else {
                Log.d("myLog", "not charging..")
            }
            val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = level/scale.toFloat() * 100
            Log.d("myLog", "batteryPct : $batteryPct")  // 충전량 확인
        }
        
        // 6. 문자 수신, SMSActivity 만들기, MyReceiver 수정
        // 퍼미션 요청
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {isGranted ->
            if(isGranted) Log.d("myLog", "callback... granted")
            else Log.d("myLog", "callback... denied")
        }
        val status = ContextCompat.checkSelfPermission(this, "android.permission.RECEIVE_SMS")
        if(status != PackageManager.PERMISSION_GRANTED) {
            requestLauncher.launch("android.permission.RECEIVE_SMS")
        }
    }
}