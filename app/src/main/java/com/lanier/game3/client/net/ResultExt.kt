package com.lanier.game3.client.net

import com.lanier.game3.client.model.BaseAPIModel
import com.lanier.game3.client.net.exception.ReqFailedException

fun <T> BaseAPIModel<T>.handleAPIResp(): T {
    return if (code == 0) {
        Result.success(data)
    } else {
        Result.failure(ReqFailedException(code, message))
    }.getOrThrow()
}