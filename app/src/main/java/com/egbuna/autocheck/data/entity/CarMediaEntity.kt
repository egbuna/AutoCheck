package com.egbuna.autocheck.data.entity

import com.egbuna.autocheck.data.model.CarMediaModel

data class CarMediaEntity(
    val id: Long,
    val name: String,
    val url: String,
    val createdAt: String,
    val type: String
)

fun CarMediaEntity.toCarMediaModel() = CarMediaModel(
    name, url, type
)