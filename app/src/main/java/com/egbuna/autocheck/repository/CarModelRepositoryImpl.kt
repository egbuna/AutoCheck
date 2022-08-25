package com.egbuna.autocheck.repository

import com.egbuna.autocheck.data.MockHelper
import com.egbuna.autocheck.data.entity.*
import com.egbuna.autocheck.data.model.CarModel
import com.egbuna.autocheck.data.model.TopCarModel
import com.egbuna.autocheck.data.remote.AutoCheckApi
import com.egbuna.autocheck.state.CarUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CarModelRepositoryImpl @Inject constructor(
    private val mockHelper: MockHelper,
    private val api: AutoCheckApi
) : CarModelRepository {

    override fun fetchTopCars() = flow {
        try {

            val topCarModels = api.getTopCarModels()
            emit(CarUiState.Success(topCars = topCarModels.makeList.map {
                it.toTopCarModel() }
            ))
        } catch (e: HttpException) {
            emit(
                CarUiState.Error(
                    message = e.localizedMessage ?: "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(CarUiState.Error(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        } catch (e: Exception) {
            emit(CarUiState.Error(message = e.localizedMessage ?: "Something went wrong"))
        }
    }

    override suspend fun fetchCars(page: Int, perPage: Int): MainCarEntity {
        return api.getAllCars(page, perPage)
    }

    override fun getCarDetail(carId: String): Flow<CarUiState> = flow {
        try {

            val carDetail = api.getCarDetails(carId)
            emit(CarUiState.Success(carDetail = carDetail.toCarDetailModel()
            ))
        } catch (e: HttpException) {
            emit(
                CarUiState.Error(
                    message = e.localizedMessage ?: "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(CarUiState.Error(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        } catch (e: Exception) {
            emit(CarUiState.Error(message = e.localizedMessage ?: "Something went wrong"))
        }
    }

    override fun getCarMedia(carId: String): Flow<CarUiState> = flow {
        try {

            val carMedia = api.getCarMedia(carId)
            emit(CarUiState.Success(carMedia = carMedia.carMediaList.map { it.toCarMediaModel() }
            ))
        } catch (e: HttpException) {
            emit(
                CarUiState.Error(
                    message = e.localizedMessage ?: "Something went wrong"
                )
            )
        } catch (e: IOException) {
            emit(CarUiState.Error(message = "Couldn't complete the request, Please make sure you're connected to the internet"))
        } catch (e: Exception) {
            emit(CarUiState.Error(message = e.localizedMessage ?: "Something went wrong"))
        }
    }
}