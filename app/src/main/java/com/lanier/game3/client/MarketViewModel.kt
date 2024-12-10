package com.lanier.game3.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.client.model.MarketModel
import com.lanier.game3.client.model.ItemType
import com.lanier.game3.client.net.APIRequester
import com.lanier.game3.client.net.handleAPIResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/2 01:23
 */
class MarketViewModel : ViewModel() {

    private val limit = 20

    var page: Int = 1
        private set

    var isEnd: Boolean = false
        private set

    private val isLoading = AtomicBoolean(false)

    private val _marketItemsLiveData = MutableLiveData<Triple<Int, List<MarketModel>, Boolean>>()
    val marketItemsLiveData: LiveData<Triple<Int, List<MarketModel>, Boolean>> = _marketItemsLiveData

    private var marketJob: Job? = null

    var type: ItemType? = null

    fun load(refresh: Boolean = false) {
        val mType = type ?: return
        if (isLoading.get()) return
        if (refresh) {
            page = 1
        } else {
            if (isEnd) return
        }
        val oldJob = marketJob
        marketJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            isLoading.set(true)
            runCatching {
                withContext(Dispatchers.IO) {
                    APIRequester.game3Api
                        .getListedProducts(
                            type = mType.type,
                            offset = (page - 1) * limit,
                            limit = limit
                        )
                        .handleAPIResp()
                }
            }.onSuccess { list ->
                val isEnd = list.size < limit
                _marketItemsLiveData.postValue(
                    Triple(page, list, isEnd)
                )
                if (isEnd.not()) page ++
            }.onFailure {
                println(">>>> ${it.message}")
            }
            isLoading.set(false)
            marketJob = null
        }
    }
}