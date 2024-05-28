package algorithmsTests.searchCycleTests

import algorithms.searchCycle.SearchCycleForVertexInGraphSolver
import graphs.edges.Edge
import graphs.graphs.Graph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

open class SearchCycleForVertexInGraphSolverTests {
    private var graph: Graph<Int> = Graph()
    protected lateinit var solver: SearchCycleForVertexInGraphSolver<Int>

    @Test
    @DisplayName("empty graph with no cycle")
    fun emptyGraph() {
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = emptyList<Int>()
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("isolated vertices with no cycle")
    fun isolatedVertices() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3))
        )
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = emptyList<Int>()
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }

    @Test
    @DisplayName("no cycle")
    fun noCycle() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2)))
        )
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        assertTrue(solver.getCycle().isEmpty())
    }

    @Test
    @DisplayName("one cycle")
    open fun oneCycle() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(0, 2)))
        )
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = true
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = listOf(0, 1, 2, 0)
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)

        assertTrue(solver.searchCycleForVertex(1))
        assertTrue(solver.searchCycleForVertex(2))
    }

    @Test
    @DisplayName("two cycles, vertex is in the first one")
    open fun twoCycles() {
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
        solver = SearchCycleForVertexInGraphSolver(graph)

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
    @DisplayName("two connected components, vertex is not in the second one")
    fun twoComponents() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(3, 2))),
            Pair(2, Edge(Pair(4, 3))),
            Pair(3, Edge(Pair(2, 4)))
        )
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = listOf(3, 2, 4, 3)
        val actualCycle = solver.getCycle()
        assertNotEquals(expectedCycle, actualCycle)
        assertEquals(emptyList<Int>(), actualCycle)
    }

    @Test
    @DisplayName("cycleOfTwoVertices")
    open fun cycleOfTwo() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2))
        )
        graph.edges = mutableMapOf(
            Pair(0, Edge(Pair(0, 1))),
            Pair(1, Edge(Pair(1, 0)))
        )
        solver = SearchCycleForVertexInGraphSolver(graph)

        val expected = false
        val actual = solver.searchCycleForVertex(0)
        assertEquals(expected, actual)

        val expectedCycle = emptyList<Int>()
        val actualCycle = solver.getCycle()
        assertEquals(expectedCycle, actualCycle)
    }
}