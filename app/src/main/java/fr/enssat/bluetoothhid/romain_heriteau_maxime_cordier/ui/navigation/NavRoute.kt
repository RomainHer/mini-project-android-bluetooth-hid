package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.navigation

sealed class NavRoute(val path: String) {

    object Home: NavRoute("home")
    object BluetoothPage: NavRoute("bluetooth")

}


