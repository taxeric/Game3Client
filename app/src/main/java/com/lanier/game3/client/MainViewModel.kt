package com.lanier.game3.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.client.cache.AppCacheData
import com.lanier.game3.client.model.Land
import com.lanier.game3.client.net.APIRequester
import com.lanier.game3.client.net.handleAPIResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/11/26 01:33
 */
class MainViewModel : ViewModel() {

    private val _lands = MutableLiveData<List<Land>>()
    val land: LiveData<List<Land>> = _lands

    private var landDetailsJob: Job? = null

    fun loadLands(
        onError: (String) -> Unit,
    ) {
        val oldJob = landDetailsJob
        landDetailsJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()

            val uid = AppCacheData.loginUser.id.toString()

            runCatching {
                withContext(Dispatchers.IO) {
                    APIRequester.game3Api
                        .getLandDetails(uid)
                        .handleAPIResp()
                }
            }.onSuccess {
                _lands.postValue(it)
            }.onFailure {
                onError.invoke("获取土地信息失败")
            }

            landDetailsJob = null
        }
    }
}