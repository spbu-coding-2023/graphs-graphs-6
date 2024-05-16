package graphsTests

import graphs.edges.Edge
import graphs.graphs.Graph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GraphTests : AbstractGraphTests<Edge, Graph<Int>>() {
    @BeforeEach
    override fun setup() {
        graph = Graph()
    }

    override fun createEdge(verticesNumbers: Pair<Int, Int>) = Edge(verticesNumbers)

    @Test
    @DisplayName("add edge opposite to existent")
    override fun addEdgeOppositeExistent() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertFalse(graph.addEdge(1, 0))
        val expectedEdges = mutableMapOf(Pair(0, Edge(Pair(0, 1))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }
}
