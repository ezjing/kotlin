package com.busanit.ch10_notification

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.busanit.ch10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 매니페스트 파일에 uses-permission 설정

        // 2. 퍼미션 확인
        val status = ContextCompat.checkSelfPermission(this, "android." + "permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED) {
            Log.d("myLog", "permission granted")
        }
        else {
            Log.d("myLog", "permission denied")
        }

        // 3. 퍼미션 요청하기 ActivityResultLauncher 객체 이용(registerForActivityResult 메서드 호출해서)
//        val requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission(),
//        ) {isGranted ->
//            if(isGranted) {
//                Log.d("myLog", "callback, granted...")
//            }
//            else {
//                Log.d("muLog", "callback, denied...")
//            }
//        }
//        // 요청
//        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")

        // 4. 복수의 퍼미션 요청하기(한번에 fine, coarse location 요청)
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            Log.d("myLog", it[android.Manifest.permission.ACCESS_FINE_LOCATION].toString())
            if (it[android.Manifest.permission.ACCESS_FINE_LOCATION] != true ||
                    it[android.Manifest.permission.ACCESS_COARSE_LOCATION] != true) {
                        Log.d("muLog", "callback, denied...")
            }
            else {
                Log.d("myLog", "callback, granted...")
            }

        }
        // 요청, 배열 이용
        requestPermissionLauncher.launch((arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)))

        val btnToast = binding.btnToast
        btnToast.setOnClickListener {
//            Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show()
            // 메시지가 보이거나 사라지는 순간 감지해서 특정 로직 수행
            showToast()
        }
    }

    private fun showToast() {
        val toast = Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            toast.addCallback(
                object : Toast.Callback() {
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("myLog", "Toast hidden")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("myLog", "Toast shown")
                    }
                }
            )
        }
        toast.show()
    }
}