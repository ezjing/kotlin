package com.busanit.ch11_recyclerview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
    // 아이템들이 배치되기 전에 호출
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.baseball), 0f, 0f, null)    // baseball 이미지는 아이템들이 배치되기 전에 출력 됐기 때문에 아이템 뒤에 가려져 보인다
    }
    
    // 아이템들이 배치된 후에 호출
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        // 뷰 크기 계산
        val width = parent.width
        val height = parent.height

        // 이미지 크기 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth    // 이미지의 실제 가로
        val drHeight = dr?.intrinsicHeight  // 이미지의 실제 세로

        // 이미지가 그려질 위치 계산
        val left = width/2 - drWidth?.div(2) as Int
        val top = height/2 - drHeight?.div(2) as Int
        c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),  // kbo 이미지는 아이템들이 배치된 후 출력되기 때문에 항상 위에 있다
        left.toFloat(), top.toFloat(), null)
    }

    override fun getItemOffsets(    // 개별 항목 꾸미기
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1
        if(index%3 == 0) {
            outRect.set(30, 30, 30, 60) // 3의 배수인 경우 아래 여백 키우기
        }
        else {
            outRect.set(10, 10, 10, 0)
        }
        view.setBackgroundColor(Color.LTGRAY)   // 뷰홀더 배경색 지정
        ViewCompat.setElevation(view, 20.0f)    // 그림자 효과
    }
}