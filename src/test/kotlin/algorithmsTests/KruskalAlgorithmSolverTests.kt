package algorithmsTests

import model.algorithms.kruskalAlgorithm.KruskalAlgorithmSolver
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class KruskalAlgorithmSolverTests {

    private lateinit var graph: WeightedGraph<Int>
    private lateinit var solver: KruskalAlgorithmSolver<Int>

    @BeforeEach
    fun setup() {
        graph = WeightedGraph()
    }

    @Test
    @DisplayName("empty graph test")
    fun emptyGraph() {
        graph.lastVertexNumber = graph.vertices.size

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = emptyList<WeightedEdge>()
        val expectedResult = Pair(false, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("graph with one vertex")
    fun oneVertex() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1))
        )
        graph.lastVertexNumber = graph.vertices.size

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = emptyList<WeightedEdge>()
        val expectedResult = Pair(false, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("isolated vertices test")
    fun isolatedVertices() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.lastVertexNumber = graph.vertices.size

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = emptyList<WeightedEdge>()
        val expectedResult = Pair(false, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("not connected graph")
    fun notConnectedGraph() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(2, 3))),
            Pair(3, WeightedEdge(Pair(4, 5)))
        )
        graph.changeEdgeWeight(0, 5)
        graph.changeEdgeWeight(1, 1)
        graph.changeEdgeWeight(2, 3)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = emptyList<WeightedEdge>()
        val expectedResult = Pair(false, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("two vertices, one edge")
    fun oneEdge() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2)))
        )
        graph.changeEdgeWeight(0, 2)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = listOf(graph.edges[1])
        val expectedResult = Pair(true, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("big minimal spanning tree test")
    fun bigMST() {
        graph.vertices = mutableMapOf(
            Pair(1, Vertex(1)),
            Pair(2, Vertex(2)),
            Pair(3, Vertex(3)),
            Pair(4, Vertex(4)),
            Pair(5, Vertex(5))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(1, 2))),
            Pair(2, WeightedEdge(Pair(3, 4))),
            Pair(3, WeightedEdge(Pair(1, 4))),
            Pair(4, WeightedEdge(Pair(4, 5))),
            Pair(5, WeightedEdge(Pair(3, 2))),
            Pair(6, WeightedEdge(Pair(2, 5)))
        )
        graph.changeEdgeWeight(1, 3)
        graph.changeEdgeWeight(2, 6)
        graph.changeEdgeWeight(3, 5)
        graph.changeEdgeWeight(4, 4)
        graph.changeEdgeWeight(5, 8)
        graph.changeEdgeWeight(6, 7)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = listOf(
            graph.edges[1],
            graph.edges[4],
            graph.edges[3],
            graph.edges[2]
        )
        val expectedResult = Pair(true, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }
}