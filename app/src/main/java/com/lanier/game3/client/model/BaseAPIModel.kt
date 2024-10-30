package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:20
 */
class BaseAPIModel<T>(
    val code: Int,
    val message: String,
    val data: T,
    val timestamp: Long,
)