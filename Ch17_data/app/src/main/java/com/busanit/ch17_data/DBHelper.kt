package com.busanit.ch17_data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "testDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // DBHelper 클래스가 처음 호출될 때 한번 호출, 테이블 생성
        db?.execSQL("create table USER_TB (" +
                "_id integer primary key autoincrement, " +
                "name text not null, " +
                "phone text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 생성자로 전달되는 버전 정보가 변경될 때 호출
    }
}