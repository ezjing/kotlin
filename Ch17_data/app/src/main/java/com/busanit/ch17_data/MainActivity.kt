package com.busanit.ch17_data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.busanit.ch17_data.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editName = binding.editName
        val editTel = binding.editTel
        val btnInsert = binding.btnInsert
        val btnSelect = binding.btnSelect
        val textView = binding.textView

        // 1. openOrCreateDatabase 활용 (해당 DB 가 있으면 사용 없으면 생성)
        val db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null)
        // 테이블 생성
        db.execSQL("create table if not exists USER_TB (" +
            "_id integer primary Key autoincrement, " +
            "name text not null, " +
            "phone text)")

        // 입력(디바이스파일익스플로러 - data - data - 부산it ch17 - databases에서 확인 가능(test.db))
        // test.db 다른이름으로 저장, sqlite 다운(sqlite browser), 실행시켜 확인 가능
        btnInsert.setOnClickListener {
            db.execSQL("insert into USER_TB (name, phone) values (?, ?)",
            arrayOf<String>(editName.text.toString(), editTel.text.toString())
            )

            Toast.makeText(this, "입력되었습니다.", Toast.LENGTH_SHORT).show()
            editName.text.clear()
            editTel.text.clear()
        }

        // 조회
        btnSelect.setOnClickListener {
            val cursor = db.rawQuery("select * from USER_TB", null)
            var str = StringBuilder()
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val phone = cursor.getString(2)
                    Log.d("myLog", "name : $name, phone : $phone")
                    str.append("$id.name : $name, phone : $phone\n")
                }
                textView.text = str
            }
        }
    }
}