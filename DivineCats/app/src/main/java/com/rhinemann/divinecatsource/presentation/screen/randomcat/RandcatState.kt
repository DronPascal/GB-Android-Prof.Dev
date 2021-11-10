package com.rhinemann.divinecatsource.presentation.screen.randomcat

/**
 * Created by dronpascal on 04.11.2021.
 */
sealed class SaveBtnState {
    object Saved : SaveBtnState()
    object Idle : SaveBtnState()
}

sealed class RandcatEvent {

    sealed class OpenScreen : RandcatEvent() {
        object CatFullscreen : RandcatEvent.OpenScreen()
    }
}