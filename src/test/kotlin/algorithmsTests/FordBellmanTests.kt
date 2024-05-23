package algorithmsTests

import model.algorithms.pathSearch.fordBellman.FordBellmanAlgorithm
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FordBellmanTests {
    private lateinit var graph: WeightedDirectedGraph<Int>
    private lateinit var solver: FordBellmanAlgorithm<Int>

    @BeforeEach
    fun setup() {
        graph = WeightedDirectedGraph()
        solver = FordBellmanAlgorithm(graph)
    }

    @Test
    @DisplayName("search path, graph consists of two vertices connected with one edge")
    fun twoVerticesOneEdge() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)))
        graph.edges = mutableMapOf(Pair(1, WeightedEdge(Pair(1, 2))))
        graph.edges[1]?.weight = 5
        val expectedPath = listOf(Vertex(1), Vertex(2))
        assertEquals(expectedPath, solver.findPath(1, 2)?.vertexList)
    }

    @Test
    @DisplayName("choose path between two variants")
    fun twoPathVariants() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(2, 3))),
            Pair(3, WeightedEdge(Pair(1, 3)))
        )
        graph.edges[1]?.weight = 5
        graph.edges[2]?.weight = -10000
        graph.edges[3]?.weight = 1
        val expectedPath = listOf(Vertex(1), Vertex(2), Vertex(3))
        assertEquals(expectedPath, solver.findPath(1, 3)?.vertexList)
    }

    @Test
    @DisplayName("search path between non-existent vertices")
    fun searchPathBetweenNonExistentVertices() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)))
        graph.edges = mutableMapOf(Pair(1, WeightedEdge(Pair(1, 2))))
        graph.edges[1]?.weight = 5
        assertNull(solver.findPath(3, 4))
    }

    @Test
    @DisplayName("search path between existent and non-existent vertices")
    fun searchPathBetweenExistentAndNonExistentVertices() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)))
        graph.edges = mutableMapOf(Pair(1, WeightedEdge(Pair(1, 2))))
        graph.edges[1]?.weight = 5
        assertNull(solver.findPath(2, 4))
    }

    @Test
    @DisplayName("search path between two non-connected vertices")
    fun searchPathNonConnectedVertices() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)), Pair(4, Vertex(4)))
        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(3, 4)))
        )
        graph.edges[1]?.weight = 5
        graph.edges[2]?.weight = 10
        assertNull(solver.findPath(1, 3))
    }

    @Test
    @DisplayName("search path with positive cycle in graph")
    fun searchPathPositiveCycle() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(2, 3))),
            Pair(3, WeightedEdge(Pair(3, 1)))
        )
        graph.edges[1]?.weight = 5
        graph.edges[2]?.weight = 10
        graph.edges[3]?.weight = 20
        val expectedPath = listOf(Vertex(1), Vertex(2), Vertex(3))
        assertEquals(expectedPath, solver.findPath(1, 3)?.vertexList)
    }

    @Test
    @DisplayName("try to search path with negative cycle in graph")
    fun searchPathNegativeCycle() {
        graph.vertices = mutableMapOf(Pair(1, Vertex(1)), Pair(2, Vertex(2)), Pair(3, Vertex(3)))
        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(2, 3))),
            Pair(3, WeightedEdge(Pair(3, 1)))
        )
        graph.edges[1]?.weight = -5
        graph.edges[2]?.weight = -10
        graph.edges[3]?.weight = -20
        assertThrows(IllegalArgumentException::class.java) {
            solver.findPath(1, 3)
        }
    }
}
