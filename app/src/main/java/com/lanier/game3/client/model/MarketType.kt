package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 01:29
 */
sealed interface MarketType {

    val type: Int

    data object Seed : MarketType {
        override val type: Int
            get() = 1
    }
}