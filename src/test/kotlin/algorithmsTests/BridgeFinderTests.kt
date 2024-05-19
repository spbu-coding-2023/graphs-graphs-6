package algorithmsTests

import algorithms.bridgeFinder.BridgeFinder
import graphs.graphs.Graph
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class BridgeFinderTests {
    private lateinit var graph: Graph<Int>
    private lateinit var finder: BridgeFinder<Int>

    @BeforeEach
    fun setup() {
        graph = Graph<Int>()
        finder = BridgeFinder(graph)
    }

    @Test
    @DisplayName("Empty graph")
    fun emptyGraph() {
        val result = finder.findBridges()
        val expectedResult = listOf<Pair<Int, Int>>()
        assertContentEquals(result, expectedResult)
    }

    @Test
    @DisplayName("Graph with some vertex, but without edges")
    fun withVertexWithoutEdges() {
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)
        graph.addVertex(5)
        graph.addVertex(6)
        graph.addVertex(7)

        val result = finder.findBridges()
        val expectedResult = listOf<Pair<Int, Int>>()
        assertContentEquals(result, expectedResult)
    }

    @Test
    @DisplayName("No bridges in graph")
    fun noBridges() {
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addEdge(0, 1)
        graph.addEdge(1, 3)
        graph.addEdge(0, 2)
        graph.addEdge(2, 3)
        val result = finder.findBridges()
        val expectedResult = listOf<Pair<Int, Int>>()
        assertContentEquals(result, expectedResult)
    }

    @Test
    @DisplayName("two components connected with bridge")
    fun twoComponentsConnectedWithBriddge() {
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)
        graph.addVertex(5)
        graph.addVertex(6)
        graph.addVertex(7)
        graph.addEdge(0, 1)
        graph.addEdge(1, 3)
        graph.addEdge(0, 2)
        graph.addEdge(2, 3)
        graph.addEdge(4, 5)
        graph.addEdge(4, 7)
        graph.addEdge(7, 6)
        graph.addEdge(5, 6)
        graph.addEdge(3, 4)
        val result = finder.findBridges()
        val expectedResult = listOf(Pair(3, 4))
        assertContentEquals(result, expectedResult)
    }
}