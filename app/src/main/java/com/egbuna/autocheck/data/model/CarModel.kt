package com.egbuna.autocheck.data.model

data class CarModel(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val year: Int?,
    val city: String?,
    val state: String?,
    val gradeScore: Double?,
    val transmission: String?,
    val fuelType: String?,
    val marketPlacePrice: Long?,
    val mileageUnit: String?,
    val mileage: Long,
    val sellingCondition: String?,
)
