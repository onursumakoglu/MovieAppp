package com.onurberin.movieapp.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeCustomDecoration(private val iSize: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = iSize
        outRect.left = iSize/2
        outRect.right = iSize/2
    }

}