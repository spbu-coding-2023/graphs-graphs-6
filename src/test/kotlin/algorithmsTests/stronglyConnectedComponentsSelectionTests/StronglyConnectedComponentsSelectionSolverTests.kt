package algorithmsTests.stronglyConnectedComponentsSelectionTests

import algorithms.stronglyConnectedComponentsSelection.StronglyConnectedComponentsSelectionSolver
import graphs.edges.Edge
import graphs.graphs.DirectedGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StronglyConnectedComponentsSelectionSolverTests {
    private lateinit var graph: DirectedGraph<Int>
    private lateinit var solver: StronglyConnectedComponentsSelectionSolver<Int>

    @BeforeEach
    fun setup() {
        graph = DirectedGraph()
        solver = StronglyConnectedComponentsSelectionSolver(graph)
    }

    @Test
    @DisplayName("empty graph components selection")
    fun emptyGraphComponentsSelection() {
        val expectedComponents: MutableMap<Int, Int> = mutableMapOf()
        assertEquals(expectedComponents, solver.selectStronglyConnectedComponents())
    }

    @Test
    @DisplayName("isolated vertices components selection")
    fun isolatedVerticesComponentsSelection() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(2, 3))))
        val components = solver.selectStronglyConnectedComponents()
        assertNotEquals(components[1], components[2])
        assertNotEquals(components[2], components[3])
        assertNotEquals(components[1], components[3])
    }

    @Test
    @DisplayName("one component selection")
    fun oneComponentSelection() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(Pair(1, Edge(Pair(1, 2))), Pair(2, Edge(Pair(2, 3))), Pair(3, Edge(Pair(3, 1))))
        val expectedComponents = mutableMapOf(Pair(1, 1), Pair(2, 1), Pair(3, 1))
        assertEquals(expectedComponents, solver.selectStronglyConnectedComponents())
    }

    @Test
    @DisplayName("two components selection")
    fun twoComponentsSelection() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)), Pair(4, Vertex(4)))
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 1))),
            Pair(3, Edge(Pair(3, 4))),
            Pair(4, Edge(Pair(4, 3))),
            Pair(5, Edge(Pair(1, 3)))
        )
        val components = solver.selectStronglyConnectedComponents()
        assertEquals(components[1], components[2])
        assertEquals(components[3], components[4])
        assertNotEquals(components[1], components[3])
    }

    @Test
    @DisplayName("two pseudo components with bridge")
    fun bridgeBetweenPseudoComponents() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)), Pair(4, Vertex(4)))
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 1))),
            Pair(3, Edge(Pair(3, 4))),
            Pair(4, Edge(Pair(4, 3))),
            Pair(5, Edge(Pair(1, 3))),
            Pair(6, Edge(Pair(3, 1)))
        )
        val expectedComponents = mutableMapOf(Pair(1, 1), Pair(2, 1), Pair(3, 1), Pair(4, 1))
        assertEquals(expectedComponents, solver.selectStronglyConnectedComponents())
    }

    @Test
    @DisplayName("three components selection, one vertex is isolated")
    fun threeComponentsIsolatedVertex() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.edges = mutableMapOf(
            Pair(1, Edge(Pair(1, 2))),
            Pair(2, Edge(Pair(2, 1))),
            Pair(3, Edge(Pair(2, 3))),
            Pair(4, Edge(Pair(3, 4))),
            Pair(5, Edge(Pair(4, 5))),
            Pair(6, Edge(Pair(5, 4)))
        )
        val components = solver.selectStronglyConnectedComponents()
        assertEquals(components[1], components[2])
        assertNotEquals(components[1], components[3])
        assertEquals(components[4], components[5])
        assertNotEquals(components[1], components[4])
        assertNotEquals(components[3], components[4])
    }
}
