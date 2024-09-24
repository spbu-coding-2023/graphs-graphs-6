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
            Pair(0, Vertex(1))
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
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
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
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(1, WeightedEdge(Pair(0, 1))),
            Pair(2, WeightedEdge(Pair(1, 2))),
            Pair(3, WeightedEdge(Pair(3, 4)))
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
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(0, WeightedEdge(Pair(0, 1)))
        )
        graph.changeEdgeWeight(0, 2)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = listOf(graph.edges[0])
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
            Pair(0, Vertex(1)),
            Pair(1, Vertex(2)),
            Pair(2, Vertex(3)),
            Pair(3, Vertex(4)),
            Pair(4, Vertex(5))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(0, WeightedEdge(Pair(0, 1))),
            Pair(1, WeightedEdge(Pair(2, 3))),
            Pair(2, WeightedEdge(Pair(0, 3))),
            Pair(3, WeightedEdge(Pair(3, 4))),
            Pair(4, WeightedEdge(Pair(2, 3))),
            Pair(5, WeightedEdge(Pair(1, 4)))
        )
        graph.changeEdgeWeight(0, 3)
        graph.changeEdgeWeight(1, 6)
        graph.changeEdgeWeight(2, 5)
        graph.changeEdgeWeight(3, 4)
        graph.changeEdgeWeight(4, 8)
        graph.changeEdgeWeight(5, 7)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = listOf(
            graph.edges[0],
            graph.edges[3],
            graph.edges[2],
            graph.edges[1]
        )
        val expectedResult = Pair(true, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }

    @Test
    @DisplayName("another big minimal spanning tree test")
    fun anotherBigMST() {
        graph.vertices = mutableMapOf(
            Pair(0, Vertex('A'.code)),
            Pair(1, Vertex('B'.code)),
            Pair(2, Vertex('C'.code)),
            Pair(3, Vertex('D'.code)),
            Pair(4, Vertex('E'.code)),
            Pair(5, Vertex('F'.code)),
            Pair(6, Vertex('G'.code))
        )
        graph.lastVertexNumber = graph.vertices.size

        graph.edges = mutableMapOf(
            Pair(0, WeightedEdge(Pair(1, 0))),
            Pair(1, WeightedEdge(Pair(0, 2))),
            Pair(2, WeightedEdge(Pair(0, 3))),
            Pair(3, WeightedEdge(Pair(3, 2))),
            Pair(4, WeightedEdge(Pair(2, 1))),
            Pair(5, WeightedEdge(Pair(1, 4))),
            Pair(6, WeightedEdge(Pair(4, 5))),
            Pair(7, WeightedEdge(Pair(5, 6))),
            Pair(8, WeightedEdge(Pair(6, 4)))
        )

        graph.changeEdgeWeight(0, 1)
        graph.changeEdgeWeight(1, 1)
        graph.changeEdgeWeight(2, 3)
        graph.changeEdgeWeight(3, 4)
        graph.changeEdgeWeight(4, 5)
        graph.changeEdgeWeight(5, 6)
        graph.changeEdgeWeight(6, 12)
        graph.changeEdgeWeight(7, 4)
        graph.changeEdgeWeight(8, 5)

        solver = KruskalAlgorithmSolver(graph)

        val expectedMST = listOf(
            graph.edges[0],
            graph.edges[1],
            graph.edges[2],
            graph.edges[7],
            graph.edges[8],
            graph.edges[5]
        )
        val expectedResult = Pair(true, expectedMST)

        val actualResult = solver.doKruskalAlgorithm()
        val actualMST = actualResult.second

        assertEquals(expectedResult.first, actualResult.first)
        assertIterableEquals(expectedMST, actualMST)
    }
}