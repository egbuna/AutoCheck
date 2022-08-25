package com.egbuna.autocheck.data.model

data class CarDetailModel(
    val id: String,
    val year: Int,
    val mileage: Long,
    val vin: String,
    val marketplacePrice: Long,
    val sold : Boolean,
    val transmission: String,
    val mileageUnit: String,
    val interiorColor: String,
    val exteriorColor: String,
    val engineType: String,
    val gradeScore: Double,
    val websiteUrl: String,
    val fuelType: String,
    val state: String,
    val city: String,
    val sellingCondition: String,
)
