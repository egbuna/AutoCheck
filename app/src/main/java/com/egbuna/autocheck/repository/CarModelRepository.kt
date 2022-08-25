package com.egbuna.autocheck.repository

import com.egbuna.autocheck.data.entity.MainCarEntity
import com.egbuna.autocheck.data.model.CarModel
import com.egbuna.autocheck.data.model.TopCarModel
import com.egbuna.autocheck.state.CarUiState
import kotlinx.coroutines.flow.Flow

interface CarModelRepository {
    fun fetchTopCars(): Flow<CarUiState>
    suspend fun fetchCars(page: Int, perPage: Int): MainCarEntity
    fun getCarDetail(carId: String): Flow<CarUiState>
    fun getCarMedia(carId: String): Flow<CarUiState>
}