package com.example.task_diagnal_technologies.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    var spanCount: Int,
    var spacingPx: Int,
    var includeEdge: Boolean
)  : RecyclerView.ItemDecoration() {




    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view!!) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left = spacingPx - column * spacingPx / spanCount
            outRect.right = (column + 1) * spacingPx / spanCount
            if (position < spanCount) { // top edge
                outRect.top = spacingPx
            }
            outRect.bottom = spacingPx // item bottom
        } else {
            outRect.left = column * spacingPx / spanCount
            outRect.right = spacingPx - (column + 1) * spacingPx / spanCount
            if (position >= spanCount) {
                outRect.top = spacingPx // item top
            }
        }
    }


//    int position = parent.getChildAdapterPosition(view); // item position
//        int column = position % spanCount; // item column
//
//        if (includeEdge) {
//            outRect.left = spacingPx - column * spacingPx / spanCount;
//            outRect.right = (column + 1) * spacingPx / spanCount;
//
//            if (position < spanCount) { // top edge
//                outRect.top = spacingPx;
//            }
//            outRect.bottom = spacingPx; // item bottom
//        } else {
//            outRect.left = column * spacingPx / spanCount;
//            outRect.right = spacingPx - (column + 1) * spacingPx / spanCount;
//            if (position >= spanCount) {
//                outRect.top = spacingPx; // item top
//            }
//        }


}