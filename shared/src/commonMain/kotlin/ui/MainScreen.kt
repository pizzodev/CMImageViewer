package ui

import LoadImageFromUrl
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import getPlatformName


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {

    //Init view model
    viewModel.initViewModel()

    val state = viewModel.mainScreenState.collectAsState().value

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            viewModel.getRandomImages()
        }) {
            Text("Fetch random images")
        }

        AnimatedVisibility(
            visible = state.status == MainScreenStatus.loading,
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = state.status == MainScreenStatus.loaded
        ) {

            Text("Hello ${getPlatformName()}");

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            if (state.randomImages?.message?.isNotEmpty() == true) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(state.randomImages.message) { randomImageUrl ->
                            RandomImageRowItem(randomImageUrl)
                        }
                    }
                )
            } else {
                Text("No data!")
            }
        }
    }
}

@Composable
fun RandomImageRowItem(randomImageUrl: String) {
    LoadImageFromUrl(randomImageUrl)
}