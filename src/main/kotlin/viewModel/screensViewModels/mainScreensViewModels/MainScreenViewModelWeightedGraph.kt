package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import model.algorithms.stronglyConnectedComponentsSelection.StronglyConnectedComponentsSelectionSolver
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.graphsViewModel.WeightedGraphViewModel
import kotlin.random.Random

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
        representationStrategy.highlight(verticesList, Color.Green)
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

    fun selectStronglyConnectedComponents() {
        if (graph !is WeightedDirectedGraph<V>) throw IllegalArgumentException("Only directed graph supported")
        val sameGraphNoWeights: DirectedGraph<V> = DirectedGraph()
        for (vertex in graph.vertices.values) sameGraphNoWeights.addVertex(vertex.value)
        for (edge in graph.edges.values) sameGraphNoWeights.addEdge(
            edge.verticesNumbers.first,
            edge.verticesNumbers.second
        )
        val solver = StronglyConnectedComponentsSelectionSolver(sameGraphNoWeights)
        val componentsMap = solver.selectStronglyConnectedComponents()
        val componentsColors: MutableMap<Int, Color> = mutableMapOf()
        val usedTriples: MutableList<Triple<Int, Int, Int>> = mutableListOf(Triple(0, 0, 0))
        for (componentNum in componentsMap.values) {
            if (componentNum !in componentsColors.keys) {
                var randomTriple = Triple(0, 0, 0)
                while (randomTriple in usedTriples) randomTriple = Triple(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                usedTriples.add(randomTriple)
                val color = Color(randomTriple.first, randomTriple.second, randomTriple.third, 255)
                componentsColors[componentNum] = color
            }
        }
        for (vertexNum in componentsMap.keys) {
            val vertex = graph.vertices[vertexNum] ?: throw IllegalArgumentException("No such vertex in a graph model")
            val vertexViewModel = graphViewModel.verticesMap[vertex] ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            val componentNum = componentsMap[vertexNum] ?: throw IllegalArgumentException("No component found for this vertex")
            val color = componentsColors[componentNum] ?: throw IllegalArgumentException("No color found for this component")
            representationStrategy.highlight(listOf(vertexViewModel), color)
        }
    }
}