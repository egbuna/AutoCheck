package com.egbuna.autocheck.state

import com.egbuna.autocheck.data.model.CarModel

data class FetchCarUIState(
    val isLoading: Boolean = true,
    val items: List<CarModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)
