package algorithmsTests.keyVerticesSelectionTests

import algorithms.keyVerticesSelection.KeyVerticesSelectionSolverDirectedGraph
import graphs.edges.Edge
import graphs.graphs.DirectedGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class KeyVerticesSelectionSolverDirectedGraphTests : KeyVerticesSelectionSolverSimpleGraphTests() {
    @BeforeEach
    override fun setup() {
        graph = DirectedGraph()
        solver = KeyVerticesSelectionSolverDirectedGraph(graph as DirectedGraph<Int>)
    }

    @Test
    @DisplayName("select key vertices in a graph with two key vertices")
    override fun selectKeyVerticesTwoKeyVertices() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5)),
            Pair(6, Vertex(6))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 3))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(3, 4))),
            Pair(4, Edge(Pair(4, 5))),
            Pair(5, Edge(Pair(4, 6)))
        )
        val expectedKeyVertices = listOf(3, 4)
        assertEquals(expectedKeyVertices, solver.selectKeyVertices())
    }

    @Test
    @DisplayName("select key vertices in a graph with one key vertex")
    override fun selectKeyVerticesExpectingOneKeyVertex() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 3))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(4, 3))),
            Pair(4, Edge(Pair(5, 3)))
        )
        val expectedKeyVertices = listOf(3)
        assertEquals(expectedKeyVertices, solver.selectKeyVertices())
    }
}