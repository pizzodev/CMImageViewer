package ui

import api.RandomImages
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repo.RandomImageRepository

data class MainScreenState(
    val status: MainScreenStatus = MainScreenStatus.default,
    val randomImages: RandomImages? = null
)

enum class MainScreenStatus {
    default,
    loading,
    loaded
}

class MainScreenViewModel: ViewModel(), KoinComponent {

    private val postRepo: RandomImageRepository by inject()

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

    fun initViewModel() {
        if (mainScreenState.value.status == MainScreenStatus.default) {
            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.default,
                randomImages = null
            )
        }
    }

    fun getRandomImages() {
        viewModelScope.launch {
            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.loading
            )

            val response = postRepo.getRandomImages()

            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.loaded,
                randomImages = response
            )
        }
    }
}