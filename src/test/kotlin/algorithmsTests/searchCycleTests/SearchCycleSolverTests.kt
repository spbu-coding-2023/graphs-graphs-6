package algorithmsTests.searchCycleTests

import algorithms.searchCycle.SearchCycleSolver
import graphs.edges.Edge
import graphs.graphs.Graph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SearchCycleSolverTests {
    private lateinit var graph: Graph<Int>
    private lateinit var solver: SearchCycleSolver<Int>

    @BeforeEach
    fun setup() {
        graph = Graph()
    }

    @Test
    @DisplayName("empty graph with no cycle")
    fun emptyGraph() {
        solver = SearchCycleSolver(graph)

        val expected = false
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = emptyList<Int>()
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("isolated vertices with no cycle")
    fun isolatedVertices() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        solver = SearchCycleSolver(graph)

        val expected = false
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = emptyList<Int>()
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("no cycle")
    fun noCycle() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(2, 3))))
        solver = SearchCycleSolver(graph)

        val expected = false
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        assertTrue(solver.getCycle().isEmpty())
    }

    @Test
    @DisplayName("one cycle")
    fun oneCycle() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(2, 3))), Pair(3, Edge(Pair(1, 3))))
        solver = SearchCycleSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val expectedCycle = listOf(1, 2, 3, 1)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("two cycles, result is the first one")
    fun twoCycles() {
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
        solver = SearchCycleSolver(graph)

        val expected = true
        val actual = solver.searchCycle()
        assertEquals(expected, actual)

        val firstCycle = listOf(1, 2, 3, 1)
        val secondCycle = listOf(1, 4, 5, 1)
        val actualCycle = solver.getCycle()
        assertNotEquals(secondCycle, actualCycle)
        assertEquals(firstCycle, actualCycle)
    }
}