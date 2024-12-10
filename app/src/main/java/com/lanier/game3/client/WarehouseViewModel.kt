package com.lanier.game3.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.game3.client.model.BaseItemModel
import com.lanier.game3.client.model.CropModel
import com.lanier.game3.client.model.ItemType
import com.lanier.game3.client.model.SeedModel
import com.lanier.game3.client.model.WarehouseModel
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
 * Date:    2024/12/11 01:54
 */
class WarehouseViewModel : ViewModel() {

    private val _warehouseSeedListLiveData = MutableLiveData<List<WarehouseModel<SeedModel>>>()
    val warehouseSeedListLiveData: LiveData<List<WarehouseModel<SeedModel>>> = _warehouseSeedListLiveData

    private val _warehouseCropListLiveData = MutableLiveData<List<WarehouseModel<CropModel>>>()
    val warehouseCropListLiveData: LiveData<List<WarehouseModel<CropModel>>> = _warehouseCropListLiveData

    private val isLoading = AtomicBoolean(false)

    var type: ItemType? = null

    private var getMerchandiseJob: Job? = null

    fun getWarehouse() {
        val mType = type ?: return
        if (isLoading.get()) return
        val oldJob = getMerchandiseJob
        getMerchandiseJob = viewModelScope.launch {
            oldJob?.cancelAndJoin()
            runCatching {
                withContext(Dispatchers.IO) {
                    when (type) {
                        ItemType.Crop -> {
                            APIRequester.game3Api
                                .getMerchandise<CropModel>(mType.type)
                                .handleAPIResp()
                        }
                        ItemType.Seed -> {
                            APIRequester.game3Api
                                .getMerchandise<SeedModel>(mType.type)
                                .handleAPIResp()
                        }
                        null -> emptyList<WarehouseModel<BaseItemModel>>()
                    }
                }
            }.onSuccess {
                when (type) {
                    ItemType.Crop -> {
                        (it as? List<WarehouseModel<CropModel>>)?.let { datas ->
                            _warehouseCropListLiveData.value = datas
                        }
                    }
                    ItemType.Seed -> {
                        (it as? List<WarehouseModel<SeedModel>>)?.let { datas ->
                            _warehouseSeedListLiveData.value = datas
                        }
                    }
                    null -> {}
                }
            }.onFailure {
                println(">>>> error ${it.message}")
            }
            getMerchandiseJob = null
        }
    }
}