package com.lanier.game3.client.net.api

import com.lanier.game3.client.model.BaseAPIModel
import com.lanier.game3.client.model.BaseItemModel
import com.lanier.game3.client.model.CropModel
import com.lanier.game3.client.model.Land
import com.lanier.game3.client.model.LandPlantReqModel
import com.lanier.game3.client.model.LoginReqModel
import com.lanier.game3.client.model.UserModel
import com.lanier.game3.client.model.MarketModel
import com.lanier.game3.client.model.MarketType
import com.lanier.game3.client.model.SeedModel
import com.lanier.game3.client.model.WarehouseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:03
 */
interface Game3API {

    @POST("/login")
    suspend fun login(@Body model: LoginReqModel) : BaseAPIModel<UserModel>

    @GET("/land-get-details")
    suspend fun getLandDetails(
        @Query("uid") uid: String
    ) : BaseAPIModel<List<Land>>

    @POST("/land-plant")
    suspend fun plantSeed(@Body landPlantModel: LandPlantReqModel) : BaseAPIModel<Boolean>

    @GET("/get-merchandise")
    suspend fun <M: BaseItemModel> getMerchandise(
        @Query("type") type: Int
    ) : BaseAPIModel<List<WarehouseModel<M>>>

    @GET("/get-listed-products")
    suspend fun getListedProducts(
        @Query("type") type: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ) : BaseAPIModel<List<MarketModel>>

    @GET("/get-crops")
    suspend fun getCrops(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ) : BaseAPIModel<List<CropModel>>

    @GET("/get-seeds")
    suspend fun getSeeds(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ) : BaseAPIModel<List<SeedModel>>
}