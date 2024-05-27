package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.graphs.edges.Edge
import model.graphs.graphs.Graph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.graphsViewModel.GraphViewModel

class MainScreenViewModel<V>(graph: Graph<V>, representationStrategy: RepresentationStrategy) :
    AbstractMainScreenViewModel<V, Edge>(representationStrategy) {
    override val graphViewModel = GraphViewModel(graph, showVerticesLabels)
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