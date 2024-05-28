package algorithmsTests.searchCycleTests

import algorithms.searchCycle.SearchCycleForVertexInDirectedGraphSolver
import graphs.edges.Edge
import graphs.graphs.DirectedGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SearchCycleForVertexInDirectedGraphSolverTests : SearchCycleForVertexInGraphSolverTests() {
    private var graph: DirectedGraph<Int> = DirectedGraph()

    @Test
    @DisplayName("one cycle, different answer from undirected case")
    override fun oneCycle() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(2, 1))),
            Pair(2, Edge(Pair(0, 2)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = listOf(0, 1, 2, 0)
        val actualCycle = solver.getCycle()
        assertNotEquals(expectedCycle, actualCycle)
        assertEquals(emptyList<Int>(), actualCycle)
    }

    @Test
    @DisplayName("one cycle in directed case")
    fun oneCycleDirected() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 0)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = listOf(0, 1, 2, 0)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("two cycles, different answer from undirected case")
    override fun twoCycles() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(0, 2))),
            Pair(3, Edge(Pair(0, 3))),
            Pair(4, Edge(Pair(4, 3))),
            Pair(5, Edge(Pair(4, 0)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val firstCycle = listOf(0, 1, 2, 0)
        val secondCycle = listOf(0, 3, 4, 0)
        val actualCycle = solver.getCycle()
        assertNotEquals(firstCycle, actualCycle)
        assertNotEquals(secondCycle, actualCycle)
        assertEquals(emptyList<Int>(), actualCycle)
    }

    @Test
    @DisplayName("two cycles in directed case, answer is the first one")
    fun twoCyclesDirected() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 0))),
            Pair(3, Edge(Pair(0, 3))),
            Pair(4, Edge(Pair(3, 4))),
            Pair(5, Edge(Pair(4, 0)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val firstCycle = listOf(0, 1, 2, 0)
        val secondCycle = listOf(0, 3, 4, 0)
        val actualCycle = solver.getCycle()
        assertNotEquals(secondCycle, actualCycle)
        assertEquals(firstCycle, actualCycle)
    }

    @Test
    @DisplayName("previous sample, single answer is the second one")
    fun twoCyclesDirectedAnother() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(0, 2))),
            Pair(3, Edge(Pair(0, 3))),
            Pair(4, Edge(Pair(3, 4))),
            Pair(5, Edge(Pair(4, 0)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val firstCycle = listOf(0, 1, 2, 0)
        val secondCycle = listOf(0, 3, 4, 0)
        val actualCycle = solver.getCycle()
        assertNotEquals(firstCycle, actualCycle)
        assertEquals(secondCycle, actualCycle)
    }

    @Test
    @DisplayName("cycleOfTwoVertices")
    override fun cycleOfTwo() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 0)))
        )
        solver = SearchCycleForVertexInDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = listOf(0, 1, 0)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }
}