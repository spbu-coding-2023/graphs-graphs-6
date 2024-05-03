package graphsTests

import graphs.edges.WeightedEdge
import graphs.graphs.WeightedDirectedGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WeightedDirectedGraphTests : AbstractWeightedGraphTests<WeightedDirectedGraph<Int>>() {
    @BeforeEach
    override fun setup() {
        graph = WeightedDirectedGraph()
    }

    @Test
    @DisplayName("add edge opposite to existent")
    override fun addEdgeOppositeExistent() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, WeightedEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertTrue(graph.addEdge(1, 0))
        val expectedEdges = mutableMapOf(Pair(0, WeightedEdge(Pair(0, 1))), Pair(1, WeightedEdge(Pair(1, 0))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

}