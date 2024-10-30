package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 17:10
 */
data class Land(
    val bpkId: Int,
    val harvestCount: Int,
    val landId: Int,
    val lastHarvestTime: Int,
    val maxHarvestCount: Int,
    val nextStageRemainTime: Int,
    val seedId: Int,
    val status: Int,
    val userId: Int
)