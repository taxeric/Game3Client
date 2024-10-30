package com.lanier.game3.client.net.exception

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:25
 */
class ReqFailedException(
    val code: Int,
    override val message: String?
) : Throwable()