package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:47
 */
data class MarketModel(
    val id: Int?,
    val isListed: Boolean,
    val itemId: Int?,
    val itemType: Int?,
    val name: String?,
    val price: Int?,
    val desc: String?,
) {

    val priceStr
        get() = "${price ?: "?"}"
}