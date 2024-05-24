import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.graphs.graphs.Graph
import view.mainScreen
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

@Composable
@Preview
fun app() {
    MaterialTheme {
        mainScreen(MainScreenViewModel(sampleGraph, CircularPlacementStrategy()))
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}