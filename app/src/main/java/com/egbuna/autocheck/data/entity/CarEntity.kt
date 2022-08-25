package com.egbuna.autocheck.data.entity

import com.egbuna.autocheck.data.model.CarModel

data class CarEntity(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val year: Int?,
    val city: String?,
    val state: String?,
    val gradeScore: Double?,
    val sellingCondition: String?,
    val hasWarranty: Boolean?,
    val marketplacePrice: Long?,
    val marketPlaceOldPrice: Long?,
    val hasFinancing: Boolean?,
    val mileage: Long?,
    val mileageUnit: String?,
    val installment: Int?,
    val depositReceived: Boolean?,
    val loanValue: Double?,
    val websiteUrl: String?,
    val stats:  CarStatsEntity?,
    val bodyTypeId: Int?,
    val sold : Boolean?,
    val hasThreeDImage:Boolean?,
    val transmission: String?,
    val fuelType: String?,
    val marketplaceVisibleDate: String
)

fun CarEntity.toCarModel(): CarModel {
    return CarModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        year = year,
        city = city,
        state = state,
        gradeScore = gradeScore,
        transmission = transmission,
        fuelType = fuelType,
        marketPlacePrice = marketplacePrice,
        mileageUnit = mileageUnit,
        mileage = mileage ?: 0,
        sellingCondition = sellingCondition
    )
}

data class CarStatsEntity(
    val webViewCount: Int?,
    val webViewerCount: Int?,
    val interestCount: Int?,
    val testDriveCount: Int?,
    val appViewerCount: Int?,
    val processedLoanCount: Int?
)