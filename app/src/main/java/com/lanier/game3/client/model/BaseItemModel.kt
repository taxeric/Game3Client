package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/28 01:31
 */
open class BaseItemModel {

    companion object {

        const val INVALID_TYPE = -1
    }

    var name: String = ""
    var price: Int = 0
    var desc: String? = ""

    open val itemType: Int = INVALID_TYPE
}