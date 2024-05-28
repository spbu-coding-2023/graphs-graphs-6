package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.graphsViewModel.WeightedGraphViewModel

class MainScreenViewModelWeightedGraph<V>(val graph: WeightedGraph<V>, representationStrategy: RepresentationStrategy) :
    AbstractMainScreenViewModel<V, WeightedEdge>(representationStrategy) {
    override val graphViewModel = WeightedGraphViewModel(graph, showVerticesLabels, showEdgesLabels)

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
            val vertexViewModel = graphViewModel.verticesMap[vertex]
                ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            verticesList.add(vertexViewModel)
        }
        representationStrategy.highlight(verticesList)
    }

    fun selectKeyVertices() {
        val sameGraphNoWeights: Graph<V> = if (graph is WeightedDirectedGraph<V>) DirectedGraph() else Graph()
        for (vertex in graph.vertices.values) sameGraphNoWeights.addVertex(vertex.value)
        for (edge in graph.edges.values) sameGraphNoWeights.addEdge(
            edge.verticesNumbers.first,
            edge.verticesNumbers.second
        )
        val solver = KeyVerticesSelectionSolver(sameGraphNoWeights)
        val keyVerticesList = solver.selectKeyVertices()
        setVerticesColor(keyVerticesList)
    }
}