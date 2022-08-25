package com.egbuna.autocheck.data.entity

data class MainCarEntity(
    val result: List<CarEntity>,
    val pagination: Pagination
)

data class Pagination(
    val total: Long,
    val currentPage: Int,
    val pageSize: Int
)
