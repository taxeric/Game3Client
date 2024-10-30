package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:35
 */
data class SeedModel(
    val seedId: Int,
    val cropId: Int?,
    val name: String,
    val price: Int,
    val desc: String,
    val season: Int,
    val maxHarvestCount: Int,
    val cropExpPer: Int,
    val singleHarvestAmount: Int,
    val stageInfo: String,
    val plantLevel: Int,
)