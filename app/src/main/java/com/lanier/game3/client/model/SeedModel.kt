package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:35
 */
data class SeedModel(
    val seedId: Int,
    val cropId: Int?,
    val season: Int,
    val maxHarvestCount: Int,
    val cropExpPer: Int,
    val singleHarvestAmount: Int,
    val stageInfo: String,
    val plantLevel: Int,
) : BaseItemModel() {

    override val itemType: Int
        get() = ItemType.Seed.type
}