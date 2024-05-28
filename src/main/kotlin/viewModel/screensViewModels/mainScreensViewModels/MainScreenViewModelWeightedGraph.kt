package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.ui.graphics.Color
import model.algorithms.bridgeFinder.BridgeFinder
import model.algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import model.algorithms.pathSearch.djikstra.DjikstraAlgorithm
import model.algorithms.pathSearch.fordBellman.FordBellmanAlgorithm
import model.algorithms.stronglyConnectedComponentsSelection.StronglyConnectedComponentsSelectionSolver
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel
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
        graphViewModel.edges.forEach { e -> e.color = Color.Black }
    }

    private fun setVerticesColor(verticesToHighlight: List<Int>) {
        val verticesList: MutableList<VertexViewModel<V>> = mutableListOf()
        for (vertexNum in verticesToHighlight) {
            val vertex = graph.vertices[vertexNum] ?: throw IllegalArgumentException("No such vertex in a graph model")
            val vertexViewModel = graphViewModel.verticesMap[vertex]
                ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            verticesList.add(vertexViewModel)
        }
        representationStrategy.highlightVertices(verticesList, Color.Green)
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
                while (randomTriple in usedTriples) randomTriple =
                    Triple(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                usedTriples.add(randomTriple)
                val color = Color(randomTriple.first, randomTriple.second, randomTriple.third, 255)
                componentsColors[componentNum] = color
            }
        }
        for (vertexNum in componentsMap.keys) {
            val vertex = graph.vertices[vertexNum] ?: throw IllegalArgumentException("No such vertex in a graph model")
            val vertexViewModel = graphViewModel.verticesMap[vertex]
                ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            val componentNum =
                componentsMap[vertexNum] ?: throw IllegalArgumentException("No component found for this vertex")
            val color =
                componentsColors[componentNum] ?: throw IllegalArgumentException("No color found for this component")
            representationStrategy.highlightVertices(listOf(vertexViewModel), color)
        }
    }

    fun findShortestPath(isFordBellman: Boolean, startVertexNum: Int, finishVertexNum: Int) {
        var previousVertex: Vertex<V>?
        val vertexList: List<Vertex<V>>
        if (isFordBellman) {
            if (graph !is WeightedDirectedGraph) throw IllegalArgumentException("Only directed graphs with negative weights are supported")
            val solver = FordBellmanAlgorithm(graph)
            val result = solver.findPath(startVertexNum, finishVertexNum)
                ?: throw IllegalArgumentException("No path between those vertices")
            previousVertex = result.sourceVertex
            vertexList = result.vertexList
        } else {
            val solver = DjikstraAlgorithm(graph)
            val result = solver.findNearestPath(startVertexNum, finishVertexNum)
            previousVertex = result.sourceVertex
            vertexList = result.vertexList
        }
        val toHighlightVertices: MutableList<VertexViewModel<V>> = mutableListOf()
        val toHighlightEdges: MutableList<EdgeViewModel<V>> = mutableListOf()
        for (vertex in vertexList) {
            val vertexViewModel = graphViewModel.verticesMap[vertex]
                ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            toHighlightVertices.add(vertexViewModel)
            for (edge in graph.edges.values) {
                if ((isFordBellman && (Pair(graph.vertices[edge.verticesNumbers.first], graph.vertices[edge.verticesNumbers.second]) == Pair(previousVertex, vertex))) || (!isFordBellman && ((Pair(graph.vertices[edge.verticesNumbers.first], graph.vertices[edge.verticesNumbers.second]) == Pair(previousVertex, vertex)) || Pair(graph.vertices[edge.verticesNumbers.first], graph.vertices[edge.verticesNumbers.second]) == Pair(vertex, previousVertex)))) {
                    val edgeViewModel =
                        graphViewModel.edgesMap[edge] ?: throw IllegalArgumentException("No ViewModel for such edge")
                    toHighlightEdges.add(edgeViewModel)
                }
            }
            previousVertex = vertex
        }
        representationStrategy.highlightVertices(toHighlightVertices, Color.Green)
        representationStrategy.highlightEdges(toHighlightEdges, Color.Red)
    }

    fun findBridges() {
        val sameGraphNoWeights = Graph<V>()
        for (vertex in graph.vertices.values) sameGraphNoWeights.addVertex(vertex.value)
        for (edge in graph.edges.values) sameGraphNoWeights.addEdge(
            edge.verticesNumbers.first,
            edge.verticesNumbers.second
        )
        val solver = BridgeFinder(sameGraphNoWeights)
        val bridges = solver.findBridges()
        val toHighlightEdges: MutableList<EdgeViewModel<V>> = mutableListOf()
        for (bridge in bridges) {
            for (edge in graph.edges.values) {
                if ((bridge == edge.verticesNumbers) || (Pair(bridge.second, bridge.first) == edge.verticesNumbers)) {
                    val edgeViewModel =
                        graphViewModel.edgesMap[edge] ?: throw IllegalArgumentException("No ViewModel for such edge")
                    toHighlightEdges.add(edgeViewModel)
                }
            }
        }
        representationStrategy.highlightEdges(toHighlightEdges, Color.Red)
    }
}