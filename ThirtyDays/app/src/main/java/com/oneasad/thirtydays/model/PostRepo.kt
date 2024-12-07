package com.oneasad.thirtydays.model

import com.oneasad.thirtydays.R

class PostRepo {
    fun getPosts(): List<Post> {

        val titleIds = arrayOf(
            R.string.title_1, R.string.title_2, R.string.title_3, R.string.title_4, R.string.title_5,
            R.string.title_6, R.string.title_7, R.string.title_8, R.string.title_9, R.string.title_10,
            R.string.title_11, R.string.title_12, R.string.title_13, R.string.title_14, R.string.title_15,
            R.string.title_16, R.string.title_17, R.string.title_18, R.string.title_19, R.string.title_20,
            R.string.title_21, R.string.title_22, R.string.title_23, R.string.title_24, R.string.title_25,
            R.string.title_26, R.string.title_27, R.string.title_28, R.string.title_29, R.string.title_30
        )

        val descriptionIds = arrayOf(
            R.string.description_1, R.string.description_2, R.string.description_3, R.string.description_4, R.string.description_5,
            R.string.description_6, R.string.description_7, R.string.description_8, R.string.description_9, R.string.description_10,
            R.string.description_11, R.string.description_12, R.string.description_13, R.string.description_14, R.string.description_15,
            R.string.description_16, R.string.description_17, R.string.description_18, R.string.description_19, R.string.description_20,
            R.string.description_21, R.string.description_22, R.string.description_23, R.string.description_24, R.string.description_25,
            R.string.description_26, R.string.description_27, R.string.description_28, R.string.description_29, R.string.description_30
        )

        val imageIds = arrayOf(
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5,
            R.drawable.image_6, R.drawable.image_7, R.drawable.image_8, R.drawable.image_9, R.drawable.image_10,
            R.drawable.image_11, R.drawable.image_12, R.drawable.image_13, R.drawable.image_14, R.drawable.image_15,
            R.drawable.image_16, R.drawable.image_17, R.drawable.image_18, R.drawable.image_19, R.drawable.image_20,
            R.drawable.image_21, R.drawable.image_22, R.drawable.image_23, R.drawable.image_24, R.drawable.image_25,
            R.drawable.image_26, R.drawable.image_27, R.drawable.image_28, R.drawable.image_29, R.drawable.image_30
        )

        val posts = mutableListOf<Post>()

        for (i in titleIds.indices) {
            posts.add(
                Post(
                    day = i+1,
                    titleId = titleIds[i],
                    descriptionId = descriptionIds[i],
                    imageId = imageIds[i]
                )
            )
        }

        return posts
    }
}
