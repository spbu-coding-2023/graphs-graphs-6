package algorithmsTests.keyVerticesSelectionTests

import algorithms.keyVerticesSelection.KeyVerticesSelectionSolver
import graphs.edges.Edge
import graphs.graphs.Graph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

open class KeyVerticesSelectionSolverTests {
    protected lateinit var graph: Graph<Int>
    protected lateinit var solver: KeyVerticesSelectionSolver<Int>

    @BeforeEach
    open fun setup() {
        graph = Graph()
        solver = KeyVerticesSelectionSolver(graph)
    }

    @Test
    @DisplayName("select key vertices in a graph with one key vertex")
    open fun selectKeyVerticesExpectingOneKeyVertex() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(3, 1))),
            Pair(2, Edge(Pair(3, 2))),
            Pair(3, Edge(Pair(3, 4))),
            Pair(4, Edge(Pair(3, 5)))
        )
        val expectedKeyVertices = listOf(3)
        assertEquals(expectedKeyVertices, solver.selectKeyVertices())
    }

    @Test
    @DisplayName("select key vertices in a cycle graph")
    fun selectKeyVerticesCycle() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(3, 1)))
        )
        val expectedKeyVertices = listOf(1, 2, 3)
        assertEquals(expectedKeyVertices, solver.selectKeyVertices())
    }

    @Test
    @DisplayName("select key vertices in a graph with two key vertices")
    open fun selectKeyVerticesTwoKeyVertices() {
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
    @DisplayName("select key vertices in a graph with no edges")
    fun selectKeyVerticesNoEdges() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3))
        )
        val expectedKeyVertices: MutableList<Int> = mutableListOf()
        assertEquals(expectedKeyVertices, solver.selectKeyVertices())
    }
}