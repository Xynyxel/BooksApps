package com.dicoding.booksapp.utils

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.dicoding.booksapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

object util {
    fun LottieAnimationView.setVisible(state: Boolean) {
        if (state) {
            visibility = LottieAnimationView.VISIBLE
        } else {
            visibility = LottieAnimationView.GONE
        }
    }

    fun ChipGroup.addChip(label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = false
            isCheckable = false
            isFocusable = false
            setTextColor(ContextCompat.getColor(context, R.color.white))
            chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.design_default_color_primary))
            addView(this)
        }
    }

}