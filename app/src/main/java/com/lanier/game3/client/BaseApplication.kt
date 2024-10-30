package com.lanier.game3.client

import android.app.Application
import com.lanier.game3.client.net.APIRequester

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:55
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        APIRequester.initAPI()
    }
}