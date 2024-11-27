package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/28 01:29
 */
data class WarehouseModel<M : BaseItemModel>(
    val warehouseId: Int,
    val quantity: Int,
    val item: M
)