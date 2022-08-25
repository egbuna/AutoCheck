package com.egbuna.autocheck.data.entity

import com.egbuna.autocheck.data.model.TopCarModel


data class TopCarEntity(
    val id: Int?,
    val name: String?,
    val imageUrl: String
)


fun TopCarEntity.toTopCarModel(): TopCarModel {
    return TopCarModel(
        name, imageUrl
    )
}