package com.busanit.ch17_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch17_todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyAdapter
    var datas: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // 입력 - AddActivity에서 구현
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()  // 데이터가 바뀐게 있는지 어뎁터에 알려주는것
            }
        }

        val mainFab = binding.mainFab
        mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity :: class.java)
            requestLauncher.launch(intent)
        }
        
        // 삭제, 수정 - MyAdapter에서 구현
    }

    override fun onResume() {
        super.onResume()
        // select, DB에서 데이터 가져오기 (읽는 용도이기 때문에 readableDatabase, cursor 객체 사용)
        datas = mutableListOf()
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TODO_TB", null)
        cursor.run {
            while (moveToNext()) {
                datas?.add(cursor.getString(1)) // 인덱스 : 0 id, 1 todo, id는 자동으로 1씩 올라가서 입력 불필요
            }
        }
        db.close()

        // 리사이클러뷰 화면 구성(뷰홀더, 어댑터, 레이아웃매니저, 아이템 데코레이션)
        val mainRecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = LinearLayoutManager(this)  // 레이아웃 매니저
        adapter = MyAdapter(datas!!)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.addItemDecoration((DividerItemDecoration(this, LinearLayoutManager.VERTICAL))) // 데코레이션, 구분선 넣기
    }
}