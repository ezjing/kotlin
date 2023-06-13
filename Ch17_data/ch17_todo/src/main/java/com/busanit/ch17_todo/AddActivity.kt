package com.busanit.ch17_todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.busanit.ch17_todo.databinding.ActivityAddBinding

// 패키지 - new - activity - empty views activity
class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.addbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add_save -> {
                // insert 실행, ActivityResultLauncher 결과값 세팅
                val intent = intent
                val todoText = binding.addEditView.text.toString()
                if(todoText.isBlank()) {
                    // 입력값이 없을 경우
                    setResult(Activity.RESULT_CANCELED, intent)
                } else {
                    val db = DBHelper(this).writableDatabase
                    db.execSQL("insert into TODO_TB(todo) values(?)", arrayOf(todoText))
                    db.close()
                    intent.putExtra("result", todoText)
                    setResult(Activity.RESULT_OK, intent)
                }
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}