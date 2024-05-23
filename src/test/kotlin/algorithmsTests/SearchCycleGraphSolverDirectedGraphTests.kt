package algorithmsTests

import model.algorithms.searchCycle.SearchCycleDirectedGraphSolver
import model.graphs.edges.Edge
import model.graphs.graphs.DirectedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SearchCycleGraphSolverDirectedGraphTests : SearchCycleGraphSolverTests() {
    private var graph: DirectedGraph<Int> = DirectedGraph()

    @Test
    @DisplayName("one cycle, different answer from undirected case")
    override fun oneCycle() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(3, 2))), Pair(3, Edge(Pair(1, 3))))
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = listOf(1, 2, 3, 1)
        val actualCycle = solver.getCycle()
        assertNotEquals(expectedCycle, actualCycle)
        assertEquals(emptyList<Int>(), actualCycle)
    }

    @Test
    @DisplayName("one cycle in directed case")
    fun oneCycleDirected() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(2, 3))), Pair(3, Edge(Pair(3, 1))))
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = listOf(1, 2, 3, 1)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("two cycles, different answer from undirected case")
    override fun twoCycles() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(1, 3))),
            Pair(4, Edge(Pair(1, 4))),
            Pair(5, Edge(Pair(5, 4))),
            Pair(6, Edge(Pair(5, 1)))
        )
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val firstCycle = listOf(1, 2, 3, 1)
        val secondCycle = listOf(1, 4, 5, 1)
        val actualCycle = solver.getCycle()
        assertNotEquals(firstCycle, actualCycle)
        assertNotEquals(secondCycle, actualCycle)
        assertEquals(emptyList<Int>(), actualCycle)
    }

    @Test
    @DisplayName("two cycles in directed case, answer is the first one")
    fun twoCyclesDirected() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(3, 1))),
            Pair(4, Edge(Pair(1, 4))),
            Pair(5, Edge(Pair(4, 5))),
            Pair(6, Edge(Pair(5, 1)))
        )
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val firstCycle = listOf(1, 2, 3, 1)
        val secondCycle = listOf(1, 4, 5, 1)
        val actualCycle = solver.getCycle()
        assertNotEquals(secondCycle, actualCycle)
        assertEquals(firstCycle, actualCycle)
    }

    @Test
    @DisplayName("previous sample, single answer is the second one")
    fun twoCyclesDirectedAnother() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 3))),
            Pair(3, Edge(Pair(1, 3))),
            Pair(4, Edge(Pair(1, 4))),
            Pair(5, Edge(Pair(4, 5))),
            Pair(6, Edge(Pair(5, 1)))
        )
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val firstCycle = listOf(1, 2, 3, 1)
        val secondCycle = listOf(1, 4, 5, 1)
        val actualCycle = solver.getCycle()
        assertNotEquals(firstCycle, actualCycle)
        assertEquals(secondCycle, actualCycle)
    }

    @Test
    @DisplayName("cycleOfTwoVertices")
    override fun cycleOfTwo() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 1)))
        )
        solver = SearchCycleDirectedGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = listOf(1, 2, 1)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }
}