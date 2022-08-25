package com.egbuna.autocheck.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egbuna.autocheck.data.entity.toCarModel
import com.egbuna.autocheck.data.remote.DefaultPaginator
import com.egbuna.autocheck.repository.CarModelRepository
import com.egbuna.autocheck.state.CarUiState
import com.egbuna.autocheck.state.FetchCarUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val topCarModelRepository: CarModelRepository
) : ViewModel() {

    private val _topCarModel: MutableStateFlow<CarUiState> = MutableStateFlow(value = CarUiState.Loading)
    val topCarModel: StateFlow<CarUiState> = _topCarModel.asStateFlow()

    private val _carDetailModel: MutableStateFlow<CarUiState> = MutableStateFlow(value = CarUiState.Loading)
    val carDetailModel: StateFlow<CarUiState> = _carDetailModel.asStateFlow()

    private val _carMedia: MutableStateFlow<CarUiState> = MutableStateFlow(value = CarUiState.Loading)
    val carMedia: StateFlow<CarUiState> = _carMedia.asStateFlow()

    var state by mutableStateOf(FetchCarUIState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated =  {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextKey ->  
            val response = topCarModelRepository.fetchCars(nextKey, 50)
            Result.success(response.result.map { it.toCarModel() })
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItem()
        }
    }


    fun getTopCarModels() {
        viewModelScope.launch {
            topCarModelRepository.fetchTopCars().collect {
                _topCarModel.value = it
            }
        }
    }

    fun getCarDetails(carId: String) {
        viewModelScope.launch {
            topCarModelRepository.getCarDetail(carId).collect {
                _carDetailModel.value = it
            }
        }
    }

    fun getCarMedia(carId: String) {
        viewModelScope.launch {
            topCarModelRepository.getCarMedia(carId).collect {
                _carMedia.value = it
            }
        }
    }
}