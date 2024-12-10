package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 01:29
 */
sealed interface ItemType {

    val type: Int

    data object Seed : ItemType {
        override val type: Int
            get() = 1
    }

    data object Crop : ItemType {
        override val type: Int
            get() = 2
    }
}