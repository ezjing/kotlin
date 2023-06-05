package com.busanit.ch14_receiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.busanit.ch14_receiver.databinding.ActivitySmsBinding

class SmsActivity : AppCompatActivity() {
    lateinit var editNum: EditText
    lateinit var editContent: EditText
    lateinit var editTime: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editNum = binding.editNum
        editContent = binding.editContent
        editTime = binding.editTime
        val button = binding.button
        button.setOnClickListener {
            finish()
        }
        val intent = intent
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        if(intent != null) {
            val sender = intent.getStringExtra("sender")
            val contents = intent.getStringExtra("contents")
            val receivedDate = intent.getStringExtra("receivedDate")
            editNum.setText(sender)
            editContent.setText(contents)
            editTime.setText(receivedDate)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        processIntent(intent)
        super.onNewIntent(intent)
    }
}