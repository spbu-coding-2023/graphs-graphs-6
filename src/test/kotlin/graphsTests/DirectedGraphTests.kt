package graphsTests

import model.graphs.edges.Edge
import model.graphs.graphs.DirectedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DirectedGraphTests : AbstractGraphTests<Edge, DirectedGraph<Int>>() {
    @BeforeEach
    override fun setup() {
        graph = DirectedGraph()
    }

    override fun createEdge(verticesNumbers: Pair<Int, Int>) = Edge(verticesNumbers)

    @Test
    @DisplayName("add edge opposite to existent")
    override fun addEdgeOppositeExistent() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, Edge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertTrue(graph.addEdge(1, 0))
        val expectedEdges = mutableMapOf(Pair(0, Edge(Pair(0, 1))), Pair(1, Edge(Pair(1, 0))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("transform graph to its adjacency map")
    override fun transformGraphToMap() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(1)), Pair(1, Vertex(1)), Pair(2, Vertex(2)))
        graph.lastVertexNumber = 3
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))), Pair(1, createEdge(Pair(1, 2))), Pair(2, createEdge(Pair(0, 2))))
        graph.lastEdgeNumber = 3
        val expectedMap = mapOf(Pair(0, setOf(1, 2)), Pair(1, setOf(2)), Pair(2, setOf()))
        assertEquals(expectedMap, graph.toAdjacencyMap())
    }
}