package com.example.geographicatlas.ui.screens.countrieslist

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CountyDividerItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = topOffset
            }
            bottom = bottomOffset
            left = leftOffset
            right = leftOffset
        }
    }

    companion object {
        private const val topOffset = 32
        private const val bottomOffset = 16
        private const val leftOffset = 24
    }
}