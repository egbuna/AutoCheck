package com.egbuna.autocheck.state

import com.egbuna.autocheck.data.model.CarDetailModel
import com.egbuna.autocheck.data.model.CarMediaModel
import com.egbuna.autocheck.data.model.CarModel
import com.egbuna.autocheck.data.model.TopCarModel

sealed interface CarUiState {
    object Loading: CarUiState
    object None: CarUiState
    data class Error(val message: String): CarUiState
    data class Success(
        val topCars: List<TopCarModel>? = null,
        val cars: List<CarModel>? = null,
        val carDetail: CarDetailModel? = null,
        val carMedia: List<CarMediaModel>? = null
    ): CarUiState
}