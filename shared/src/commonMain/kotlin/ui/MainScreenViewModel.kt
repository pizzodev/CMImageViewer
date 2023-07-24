package ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel

sealed class MainScreenState {
    class loading
    class loaded (data: List<String>)
}

class MainScreenViewModel: ViewModel() {
    var shared = "Pippo!";
}