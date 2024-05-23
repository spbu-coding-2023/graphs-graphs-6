package algorithmsTests

import algorithms.djikstra.DjikstraAlgorithm
import algorithms.djikstra.DjikstraPathResult
import graphs.graphs.WeightedGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DjikstraTests {

    private lateinit var graph: WeightedGraph<Int>
    private lateinit var solver: DjikstraAlgorithm<Int>

    @BeforeEach
    fun setup() {
        graph = WeightedGraph<Int>()
        solver = DjikstraAlgorithm(graph)
    }

    fun createExpectedPathResult(vararg ids: Int): DjikstraPathResult<Int> {
        val list = mutableListOf<Vertex<Int>>()

        for (i in ids) {
            if (!graph.vertices.containsKey(i)) throw IllegalArgumentException("Vertex with id $i not found")
            list.add(graph.vertices[i]!!)
        }

        return DjikstraPathResult(graph, list)
    }
    fun assertEqualsDjikstraPathResult(expected: DjikstraPathResult<Int>, result: DjikstraPathResult<Int>) {
        assertEquals(expected.graph, result.graph)
        assertEquals(expected.vertexList.size, result.vertexList.size)
        for (i in 0..<expected.vertexList.size) {
            assertEquals(expected.vertexList[i], result.vertexList[i])
        }
    }

    fun prepareGraphSchema_First() {
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)

        graph.addEdge(0, 1)
        graph.changeEdgeWeight(0, 3)
        graph.addEdge(0, 2)
        graph.changeEdgeWeight(1, 6)
        graph.addEdge(0, 3)
        graph.changeEdgeWeight(2, 1)
        graph.addEdge(1, 4)
        graph.changeEdgeWeight(3, 8)
        graph.addEdge(2, 4)
        graph.changeEdgeWeight(4, 4)
        graph.addEdge(3, 2)
        graph.changeEdgeWeight(5, 4)
        graph.addEdge(3, 4)
        graph.changeEdgeWeight(6, 12)
    }

    @Test
    @DisplayName("First Schema 0 -> 1")
    fun firstSchema0to1() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(0, 1)
        val expresult = createExpectedPathResult(0, 1)

        assertEqualsDjikstraPathResult(expresult, result)
    }

    @Test
    @DisplayName("First Schema 0 -> 3")
    fun firstSchema0to3() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(0, 3)
        val expresult = createExpectedPathResult(0, 3)

        assertEqualsDjikstraPathResult(expresult, result)
    }

    @Test
    @DisplayName("First Schema 0 -> 4")
    fun firstSchema0to4() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(0, 4)
        val expresult = createExpectedPathResult(0, 3, 2, 4)

        assertEqualsDjikstraPathResult(expresult, result)
    }

    @Test
    @DisplayName("First Schema 4 -> 0")
    fun firstSchema4to0() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(4, 0)
        val expresult = createExpectedPathResult(4, 2, 3, 0)

        assertEqualsDjikstraPathResult(expresult, result)
    }

    @Test
    @DisplayName("First Schema 1 -> 3")
    fun firstSchema1to3() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(1, 3)
        val expresult = createExpectedPathResult(1, 0, 3)

        assertEqualsDjikstraPathResult(expresult, result)
    }

    @Test
    @DisplayName("First Schema 3 -> 1")
    fun firstSchema3to1() {
        prepareGraphSchema_First()

        val result = solver.findNearestPath(3, 1)
        val expresult = createExpectedPathResult(3, 0, 1)

        assertEqualsDjikstraPathResult(expresult, result)
    }
}