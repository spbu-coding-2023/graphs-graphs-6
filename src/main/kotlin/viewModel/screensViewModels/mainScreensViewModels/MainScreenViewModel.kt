package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import model.graphs.edges.Edge
import model.graphs.graphs.Graph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.graphsViewModel.GraphViewModel

class MainScreenViewModel<V>(val graph: Graph<V>, representationStrategy: RepresentationStrategy) :
    AbstractMainScreenViewModel<V, Edge>(representationStrategy) {
    override val graphViewModel = GraphViewModel(graph, showVerticesLabels)

    init {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
    }

    fun resetGraphView() {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
        graphViewModel.vertices.forEach { v -> v.color = Color.Gray }
    }

    private fun setVerticesColor(verticesToHighlight: List<Int>) {
        val verticesList: MutableList<VertexViewModel<V>> = mutableListOf()
        for (vertexNum in verticesToHighlight) {
            val vertex = graph.vertices[vertexNum] ?: throw IllegalArgumentException("No such vertex in a graph model")
            val vertexViewModel = graphViewModel.verticesMap[vertex] ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            verticesList.add(vertexViewModel)
        }
        representationStrategy.highlight(verticesList)
    }

    fun selectKeyVertices() {
        val solver = KeyVerticesSelectionSolver(graph)
        val keyVerticesList = solver.selectKeyVertices()
        setVerticesColor(keyVerticesList)
    }
}