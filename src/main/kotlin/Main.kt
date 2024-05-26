import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.graphs.graphs.Graph
import view.graphChooseScreen
import view.mainScreen
import viewModel.GraphChooseViewModel
import viewModel.MainScreenViewModel
import viewModel.graphViewModel.CircularPlacementStrategy

val sampleGraph = Graph<String>().apply {
    addVertex("A")
    addVertex("B")
    addVertex("C")
    addVertex("D")
    addVertex("E")
    addVertex("F")
    addVertex("G")

    addEdge(0, 1)
    addEdge(0, 2)
    addEdge(0, 3)
    addEdge(0, 4)
    addEdge(0, 5)
    addEdge(0, 6)
}

var closeFirstWindow: MutableState<Boolean> = mutableStateOf(false)

@Composable
@Preview
fun app() {
    MaterialTheme {
        closeFirstWindow = remember { mutableStateOf(false) }
        if (!closeFirstWindow.value) {
            graphChooseScreen(GraphChooseViewModel<String>()) {
                closeFirstWindow.value = true
            }
        }

        if (closeFirstWindow.value) {
            mainScreen(MainScreenViewModel(sampleGraph, CircularPlacementStrategy()))
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}