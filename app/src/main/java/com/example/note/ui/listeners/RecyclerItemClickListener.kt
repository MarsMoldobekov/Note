package com.example.note.ui.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnItemClickListener
) : RecyclerView.OnItemTouchListener {

    interface OnItemClickListener {
        fun onItemClicked(view: View, position: Int)
        fun onLongItemClicked(view: View, position: Int)
    }

    private val detector = GestureDetector(
        context,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent) = true

            override fun onLongPress(e: MotionEvent) {
                recyclerView.findChildViewUnder(e.x, e.y)?.let {
                    listener.onLongItemClicked(it, recyclerView.getChildAdapterPosition(it))
                }
            }
        }
    )

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child: View? = rv.findChildViewUnder(e.x, e.y)

        if (child != null && detector.onTouchEvent(e)) {
            listener.onItemClicked(child, rv.getChildAdapterPosition(child))
            return true
        }

        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}