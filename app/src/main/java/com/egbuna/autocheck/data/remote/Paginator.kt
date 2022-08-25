package com.egbuna.autocheck.data.remote

interface Paginator<Key, Item> {
    suspend fun loadNextItem()
    fun reset()
}