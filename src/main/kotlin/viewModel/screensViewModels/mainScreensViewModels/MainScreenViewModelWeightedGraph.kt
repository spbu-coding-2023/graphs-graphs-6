package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.graphsViewModel.WeightedGraphViewModel

class MainScreenViewModelWeightedGraph<V>(graph: WeightedGraph<V>, representationStrategy: RepresentationStrategy) :
    AbstractMainScreenViewModel<V, WeightedEdge>(representationStrategy) {
    override val graphViewModel = WeightedGraphViewModel(graph, showVerticesLabels, showEdgesLabels)

    init {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
    }

    fun resetGraphView() {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
        graphViewModel.vertices.forEach { v -> v.color = Color.Gray }
    }

    fun setVerticesColor() {
        representationStrategy.highlight(graphViewModel.vertices)
    }
}