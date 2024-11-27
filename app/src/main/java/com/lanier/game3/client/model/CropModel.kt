package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/23 23:31
 */
data class CropModel(
    val cropId: Int,
    val seedId: Int?,
    val season: Int,
) : BaseItemModel()
