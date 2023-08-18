package ui

import api.ApiClient
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class MainScreenState(
    val status: MainScreenStatus = MainScreenStatus.default,
    val posts: String? = null
)

enum class MainScreenStatus {
    default,
    loading,
    loaded
}

class MainScreenViewModel: ViewModel(), KoinComponent {

    private val apiClient: ApiClient by inject()

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

    fun initViewModel() {
        if (mainScreenState.value.status == MainScreenStatus.default) {
            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.default,
                posts = null
            )
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.loading
            )

            val response = apiClient.getPosts()

            _mainScreenState.value = _mainScreenState.value.copy(
                status = MainScreenStatus.loaded,
                posts = response
            )
        }
    }
}