package com.busanit.ch10_notification

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.busanit.ch10_notification.databinding.ActivityMainBinding
import com.busanit.ch10_notification.databinding.DialogInputBinding

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

        // 6. 날짜 선택, DatePickerDialog
        val btnDate = binding.btnDate
        val editDate = binding.editDate
        btnDate.setOnClickListener {
            val datePicker = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    editDate.setText("${year}년 ${month + 1}월 ${dayOfMonth}일")   // DatePickerDialog의 월값은 1~12월이 아닌  0~ 11월 그래서 실제 월 구하려면 month + 1
                }

            }, 2023, (5-1), 22)
            datePicker.show()
        }

        // 7. 시간 선택, TimePickerDialog (변수선언 없이 바로 타임피커 써서 바로 .show 가능)
        val btnTime = binding.btnTime
        val editTime = binding.editTime
        btnTime.setOnClickListener {
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    editTime.setText("선택한 시간은 ${hourOfDay} : ${minute}")
                }

            }, 9, 35, false).show()  // 시간 선택시 true : 24시간 표기, false : 12시간 표기.
        }

        // 8-2. AlertDialog 이벤트 핸들러 만들기
        val eventHandler = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if(which ==DialogInterface.BUTTON_POSITIVE) {
                    Toast.makeText(applicationContext, "Yes 버튼 클릭", Toast.LENGTH_SHORT).show()  // this 안되면 applicationContext. 어플리케이션 내용을 들고온다는 말
                } else if(which == DialogInterface.BUTTON_NEGATIVE) {
                    Toast.makeText(applicationContext, "No 버튼 클릭", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "More 버튼 클릭", Toast.LENGTH_SHORT).show()
                }
            }

        }

        // 8-1. 알림창 띄우기, AlertDialog
        val btnAlert = binding.btnAlert
        btnAlert.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("Text Dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
//                setPositiveButton("Yes", null)
//                setNegativeButton("No", null)
//                setNeutralButton("More", null)

                // 8-2. 이벤트 핸들러 등록
                setPositiveButton("Yes", eventHandler)
                setNegativeButton("No", eventHandler)
                setNeutralButton("More", eventHandler)
                show()
            }
        }


        // 9-1. 목록을 출력하는 알림창 띄우기
        val btnFruit = binding.btnFruit
        val editFruit = binding.editFruit
        var fruits : ArrayList<String> // 9-2. 다중 선택용
        // 9-1. 단순 목록 출력
//        btnFruit.setOnClickListener {
//            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
//            AlertDialog.Builder(this).run {
//                setTitle("과일 선택")
//                setIcon(android.R.drawable.ic_input_add)
//                setItems(items, object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        editFruit.setText("${items[which]}")
//                    }
//                })
//                setPositiveButton("닫기", null)
//                show()
//            }
//        }

        // 9-2. 다중선택, setItems -> setMultiChoiceItems
//        btnFruit.setOnClickListener {
//            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
//            fruits = arrayListOf<String>()  // 초기화
//            AlertDialog.Builder(this).run {
//                setTitle("과일 선택")
//                setIcon(android.R.drawable.ic_input_add)
//                setMultiChoiceItems(items, booleanArrayOf(false, false, false, false),
//                object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        // 클릭한 아이템의 인덱스와 선택(which: Int), 선택해제 여부(isChecked: Boolean) 전달
//                        if(isChecked) {
//                            fruits.add(items[which])
//                        } else {
//                            fruits.remove(items[which])
//                        }
//                    }
//
//                })
//                setPositiveButton("닫기", object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        var str = ""
//                        for (i in fruits)
//                            str += i + " "
//                        editFruit.setText(str)
//                    }
//
//                })
//                show()
//            }
//        }

        // 9-3. 라디오버튼, 단일 선택 setItems -> setSingleChoiceItems
        btnFruit.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("과일 선택")
                setIcon(android.R.drawable.ic_input_add)
                setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        editFruit.setText("${items[which]}")
                    }

                })
                setPositiveButton("닫기",null)
                setCancelable(false)    // 뒤로가기 버튼 클릭시 닫기 여부, 기본값 true
                show()
            }.setCanceledOnTouchOutside(false)  // 대화상자 바깥을 클릭했을 때 닫기 여부. 원래는 바깥을 누르면 알림창이 꺼져야하는데 바깥을 눌러도 안꺼지게 하는 설정(true 하면 꺼짐)
        }

        // 10. 커스텀 다이얼로그
        val btnCustom = binding.btnCustom
        btnCustom.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)  // DialogInputBinding 규칙 : 첫글자 대문자, _ 뒤 첫글자 대문자
            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root) // setView를 이용, 커스텀 다이얼로그(dialog_input.xml) 적용
                setPositiveButton("닫기", null)
                show()
            }
        }


        // 11. 소리 알림, RingtoneManager로 시스템에 등록된 소리 이용
        val btnSound = binding.btnSound
        btnSound.setOnClickListener {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        }
        
        // 12. 음원 재생, MediaPlayer 이용
        val btnPlay = binding.btnPlay
        btnPlay.setOnClickListener {
            val player: MediaPlayer = MediaPlayer.create(this, R.raw.mario)
            player.start()
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