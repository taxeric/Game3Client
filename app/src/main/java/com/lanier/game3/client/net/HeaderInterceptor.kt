package com.lanier.game3.client.net

import com.lanier.game3.client.cache.AppCacheData
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 19:52
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val token = AppCacheData.respToken
        val newReq = combineHeaders(req, token)
        return chain.proceed(newReq)
    }

    private fun combineHeaders(req: Request, token: String?): Request {
        return req.newBuilder()
            .apply {
                if (!token.isNullOrBlank()) {
                    header("Authorization", "Bearer $token")
                }
            }
            .build()
    }
}