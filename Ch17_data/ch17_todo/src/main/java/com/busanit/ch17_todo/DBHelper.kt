package com.busanit.ch17_todo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 코틀린클래스 생성
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null ,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table TODO_TB(" +
                "_id integer primary key autoincrement, " +
                "todo next not null)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { // 버전 변경시 실행되는 메소드
        db?.execSQL("drop table if exists TODO_TB") // 만약 테이블이 존재하면 삭제하고
        onCreate(db)    // 다시 만들어라
    }
}