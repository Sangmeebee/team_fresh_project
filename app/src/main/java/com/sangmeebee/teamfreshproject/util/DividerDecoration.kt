package com.sangmeebee.teamfreshproject.util

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerDecoration(heightDp: Int) : RecyclerView.ItemDecoration() {

    private val mPaint: Paint = Paint()
    private val mHeightDp: Int

    init {
        mPaint.color = Color.argb((255 / 5), 0, 0, 0)
        mHeightDp = (heightDp * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            if (i < parent.childCount - 1) {
                val view: View = parent.getChildAt(i)
                c.drawRect(view.left.toFloat(), view.bottom.toFloat(), view.right.toFloat(), (view.bottom + mHeightDp).toFloat(), mPaint)
            }
        }
    }
}
