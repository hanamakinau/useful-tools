package jp.hanamakinau.usefultools

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "useful-tools",
    ) {
        App()
    }
}