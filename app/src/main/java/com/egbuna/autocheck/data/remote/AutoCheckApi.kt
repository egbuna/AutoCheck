package com.egbuna.autocheck.data.remote

import com.egbuna.autocheck.data.entity.CarDetailEntity
import com.egbuna.autocheck.data.entity.MainCarEntity
import com.egbuna.autocheck.data.entity.MainCarMediaEntity
import com.egbuna.autocheck.data.entity.MainTopCarModelEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AutoCheckApi {

    @GET("inventory/make?popular=true")
    suspend fun getTopCarModels(): MainTopCarModelEntity

    @GET("inventory/car/search")
    suspend fun getAllCars(
        @Query("page_number") page: Int,
        @Query("page_size") perPage: Int
    ): MainCarEntity

    @GET("inventory/car/{carId}")
    suspend fun getCarDetails(
        @Path("carId") carId: String,
    ): CarDetailEntity

    @GET("inventory/car_media")
    suspend fun getCarMedia(
        @Query("carId") carId: String
    ): MainCarMediaEntity
}