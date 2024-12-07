package com.oneasad.coursesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleId: Int,
    val courses: Int,
    @DrawableRes var imageId: Int
)
