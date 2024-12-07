package com.oneasad.thirtydays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Post(
    val day: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int,
    @DrawableRes val imageId: Int,
)
