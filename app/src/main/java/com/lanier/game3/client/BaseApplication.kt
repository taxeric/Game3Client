package com.lanier.game3.client

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import com.lanier.game3.client.net.APIRequester

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:55
 */
class BaseApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        APIRequester.initAPI()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("img_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}