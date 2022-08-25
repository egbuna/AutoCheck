package com.egbuna.autocheck.data

import com.egbuna.autocheck.data.model.CarModel
import com.egbuna.autocheck.data.model.TopCarModel
import javax.inject.Inject

class MockHelper @Inject constructor() {

    val topCarModel = listOf(
        TopCarModel(
            name = "Audi",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/marketplace/audi.png"
        ),
        TopCarModel(
            name = "Toyota",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/marketplace/toyota.png"
        ),
        TopCarModel(
            name = "BMW",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/brands/bmw.svg"
        ),
        TopCarModel(
            name = "Honda",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/brands/honda.svg"
        ),
        TopCarModel(
            name = "Jaguar",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/marketplace/jaguar.jpg"
        ),
        TopCarModel(
            name = "Kai",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/brands/kia.svg"
        ),
        TopCarModel(
            name = "Lexus",
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/brands/lexus.svg"
        ),
    )

    val cars = listOf(
        CarModel(
            id = "qwe",
            title = "Toyota Corolla",
            imageUrl = "https://media.autochek.africa/file/GbZAMznM.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 250000,
              sellingCondition = "Foreign"
        ),
        CarModel(
            id = "qwer",
            title = "Toyota Camry",
            imageUrl = "https://media.autochek.africa/file/ctZdvZ0s.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 350000,
            sellingCondition = "Foreign"
        ),
        CarModel(
            id = "qwert",
            title = "Honda Accord",
            imageUrl = "https://media.autochek.africa/file/6rkqe2jk.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 750000,
            sellingCondition = "Foreign"
        ),
        CarModel(
            id = "qwerty",
            title = "Lexus 330",
            imageUrl = "https://media.autochek.africa/file/QNMeRSMq.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 350000,
            sellingCondition = "Local"
        ),
        CarModel(
            id = "qwertyu",
            title = "Lexus 330",
            imageUrl = "https://media.autochek.africa/file/Zb4zGfzQ.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 650000,
            sellingCondition = "Foreign"
        ),
        CarModel(
            id = "qwertyui",
            title = "Hyundai Elantra",
            imageUrl = "https://media.autochek.africa/file/Jp0JBQhW.webp",
            year = 2018,
            city = "Abuja",
            state = "F.C.T",
            gradeScore = 3.5,
            transmission = "automatic",
            fuelType = "fuel",
            marketPlacePrice = 3200000,
            mileageUnit = "miles",
            mileage = 670000,
            sellingCondition = "Local"
        ),
    )
}