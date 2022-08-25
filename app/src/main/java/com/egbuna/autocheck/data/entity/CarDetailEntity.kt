package com.egbuna.autocheck.data.entity

import com.egbuna.autocheck.data.model.CarDetailModel
import java.time.Year

data class CarDetailEntity(
    val id: String,
    val year: Int,
    val mileage: Long,
    val vin: String,
    val marketplacePrice: Long,
    val sold: Boolean,
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

fun CarDetailEntity.toCarDetailModel() = CarDetailModel(
    id,
    year,
    mileage,
    vin,
    marketplacePrice,
    sold,
    transmission,
    mileageUnit,
    interiorColor,
    exteriorColor,
    engineType,
    gradeScore,
    websiteUrl,
    fuelType,
    state,
    city,
    sellingCondition
)


//{
//    "model": {
//    "modelFeatures": [],
//    "id": 918,
//    "name": "ES 350",
//    "imageUrl": "",
//    "wheelType": "2WD",
//    "series": "ES Series",
//    "popular": true
//},
//    "country": "NG",
//    "inspectorDetails": {
//    "inspectedMakes": [
//    {
//        "count": 237,
//        "name": "Toyota",
//        "imageUrl": "https://storage.googleapis.com/img.autochek.africa/marketplace/toyota.png"
//    },
//    {
//        "count": 57,
//        "name": "Lexus",
//        "imageUrl": "https://storage.googleapis.com/img.autochek.africa/brands/lexus.svg"
//    },
//    {
//        "count": 17,
//        "name": "Mercedes-Benz",
//        "imageUrl": "https://storage.googleapis.com/img.autochek.africa/brands/benz.svg"
//    }
//    ],
//    "inspectorFullName": "Ajiboye Elijah  Temitayo",
//    "workshopName": "Everything Automobile",
//    "totalInspection": 380
//},
//    "carName": "Lexus ES 350 - 2010",
//    "ccMeasurement": 3500
//}