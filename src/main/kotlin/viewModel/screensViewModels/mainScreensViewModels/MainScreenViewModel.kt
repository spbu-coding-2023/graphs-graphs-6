package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import model.algorithms.bridgeFinder.BridgeFinder
import model.algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import model.algorithms.searchCycle.SearchCycleForVertexInDirectedGraphSolver
import model.algorithms.searchCycle.SearchCycleForVertexInGraphSolver
import model.algorithms.stronglyConnectedComponentsSelection.StronglyConnectedComponentsSelectionSolver
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel
import viewModel.graphViewModel.graphsViewModel.GraphViewModel
import kotlin.random.Random

class MainScreenViewModel<V>(val graph: Graph<V>, private val representationStrategy: RepresentationStrategy) {

    val showVerticesLabels = mutableStateOf(false)
    val graphViewModel = GraphViewModel(graph, showVerticesLabels)

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
        val solver = KeyVerticesSelectionSolver(graph)
        val keyVerticesList = solver.selectKeyVertices()
        setVerticesColor(keyVerticesList)
    }

    fun selectStronglyConnectedComponents() {
        if (graph !is DirectedGraph<V>) throw IllegalArgumentException("Only directed graph supported")
        val solver = StronglyConnectedComponentsSelectionSolver(graph)
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

    fun findBridges() {
        val solver = BridgeFinder(graph)
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

    fun findCycle(cycleVertexNum: Int) {
        val solver =
            if (graph is DirectedGraph<V>) SearchCycleForVertexInDirectedGraphSolver(graph) else SearchCycleForVertexInGraphSolver(
                graph
            )
        val vertexNum = cycleVertexNum - 1
        val resultHasCycle = solver.searchCycleForVertex(vertexNum)
        val resultList = solver.getCycle()
        val toHighlightVertices: MutableList<VertexViewModel<V>> = mutableListOf()
        val toHighlightEdges: MutableList<EdgeViewModel<V>> = mutableListOf()
        var previousVertexNum = vertexNum
        if (resultHasCycle) {
            for (currVertexNum in resultList) {
                val vertex =
                    graph.vertices[currVertexNum] ?: throw IllegalArgumentException("No such vertex in graph model")
                val vertexViewModel = graphViewModel.verticesMap[vertex]
                    ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
                toHighlightVertices.add(vertexViewModel)
                for (edge in graph.edges.values) {
                    if (graph is DirectedGraph) {
                        if (edge.verticesNumbers == Pair(previousVertexNum, currVertexNum)) {
                            val edgeViewModel = graphViewModel.edgesMap[edge]
                                ?: throw IllegalArgumentException("No such edge in a graph ViewModel")
                            toHighlightEdges.add(edgeViewModel)
                        }
                    } else {
                        if ((edge.verticesNumbers == Pair(previousVertexNum, currVertexNum)) || (edge.verticesNumbers == Pair(currVertexNum, previousVertexNum))) {
                            val edgeViewModel = graphViewModel.edgesMap[edge]
                                ?: throw IllegalArgumentException("No such edge in a graph ViewModel")
                            toHighlightEdges.add(edgeViewModel)
                        }
                    }
                }
                previousVertexNum = currVertexNum
            }
            representationStrategy.highlightVertices(toHighlightVertices, Color.Green)
            representationStrategy.highlightEdges(toHighlightEdges, Color.Red)
        } else {
            val vertex =
                graph.vertices[vertexNum] ?: throw IllegalArgumentException("No such vertex in graph model")
            val vertexViewModel = graphViewModel.verticesMap[vertex]
                ?: throw IllegalArgumentException("No such vertex in a graph ViewModel")
            toHighlightVertices.add(vertexViewModel)
            representationStrategy.highlightVertices(toHighlightVertices, Color.Blue)
        }
    }
}