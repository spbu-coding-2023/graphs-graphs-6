package integration

import androidx.compose.ui.graphics.Color
import model.graphs.graphs.Graph
import org.junit.jupiter.api.Test
import viewModel.graphViewModel.CircularPlacementStrategy
import viewModel.screensViewModels.mainScreensViewModels.MainScreenViewModel

class ShowBridgeFinderTest {

    @Test
    fun showBridgeFinderTest() {
        val graph = Graph<Int>()
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)
        graph.addVertex(5)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 3) // bridge
        graph.addEdge(3, 4)
        graph.addEdge(4, 5)
        graph.addEdge(5, 3)
        val representationStrategy = CircularPlacementStrategy()
        val mainScreenViewModel = MainScreenViewModel(graph, representationStrategy)

        mainScreenViewModel.findBridges()

        var hightlightedEdgesCount = 0
        mainScreenViewModel.graphViewModel.edges.forEach { if (it.color == Color.Red) hightlightedEdgesCount++ }
        assert(hightlightedEdgesCount == 1)
        val bridge = mainScreenViewModel.graphViewModel.edges.first { it.color == Color.Red }
        assert(bridge.u.v.value != bridge.v.v.value)
        assert(bridge.u.v.value == 2 || bridge.u.v.value == 3)
        assert(bridge.v.v.value == 3 || bridge.v.v.value == 2)
    }
}