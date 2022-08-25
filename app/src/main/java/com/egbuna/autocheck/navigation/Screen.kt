package com.egbuna.autocheck.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("detail")
}