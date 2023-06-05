package com.busanit.ch13_todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.busanit.ch13_todo.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var addEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.addbar)
        addEdit = binding.addEditView
    }

    // 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 저장 클릭시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add_save -> {
                // 결과 세팅하고 finish() 호출
                val intent = intent
                intent.putExtra("result", addEdit.text.toString())
                setResult(Activity.RESULT_OK, intent)   // 응답으로 보낼 결과 정리
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}