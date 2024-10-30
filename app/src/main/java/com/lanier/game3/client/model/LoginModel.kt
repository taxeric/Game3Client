package com.lanier.game3.client.model

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:14
 */
data class LoginReqModel(
    val account: String,
    val password: String,
)

data class UserModel(
    val account: String? = null,
    val balance: Int? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val lands: List<Land>? = null,
    val token: String? = null,
    val username: String? = null
)
