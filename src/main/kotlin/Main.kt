import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import view.screens.graphChooseScreen
import view.screens.mainScreens.mainScreen
import view.screens.mainScreens.mainScreenWeightedGraph
import viewModel.graphViewModel.CircularPlacementStrategy
import viewModel.screensViewModels.GraphChooseViewModel
import viewModel.screensViewModels.mainScreensViewModels.MainScreenViewModel
import viewModel.screensViewModels.mainScreensViewModels.MainScreenViewModelWeightedGraph

var closeFirstWindow: MutableState<Boolean> = mutableStateOf(false)
val graphChooseVM = GraphChooseViewModel<String>()

@Composable
@Preview
fun app() {
    MaterialTheme {
        closeFirstWindow = remember { mutableStateOf(false) }
        if (!closeFirstWindow.value) {
            graphChooseScreen(graphChooseVM) {
                closeFirstWindow.value = true
            }
        }
        if (closeFirstWindow.value) {
            if (!graphChooseVM.isDirected && !graphChooseVM.isWeighted) {
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
                mainScreen(MainScreenViewModel(sampleGraph, CircularPlacementStrategy()), false)
            } else if (graphChooseVM.isDirected && !graphChooseVM.isWeighted) {
                val sampleGraph = DirectedGraph<String>().apply {
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
                mainScreen(MainScreenViewModel(sampleGraph, CircularPlacementStrategy()), true)
            } else if (!graphChooseVM.isDirected) {
                val sampleGraph = WeightedGraph<String>().apply {
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

                    changeEdgeWeight(0, 1)
                    changeEdgeWeight(1, -2)
                    changeEdgeWeight(2, 3)
                    changeEdgeWeight(3, -4)
                    changeEdgeWeight(4, 5)
                    changeEdgeWeight(5, -6)
                }
                mainScreenWeightedGraph(MainScreenViewModelWeightedGraph(sampleGraph, CircularPlacementStrategy()), false)
            } else {
                val sampleGraph = WeightedDirectedGraph<String>().apply {
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

                    changeEdgeWeight(0, 1)
                    changeEdgeWeight(1, -2)
                    changeEdgeWeight(2, 3)
                    changeEdgeWeight(3, -4)
                    changeEdgeWeight(4, 5)
                    changeEdgeWeight(5, -6)
                }
                mainScreenWeightedGraph(MainScreenViewModelWeightedGraph(sampleGraph, CircularPlacementStrategy()), true)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}
