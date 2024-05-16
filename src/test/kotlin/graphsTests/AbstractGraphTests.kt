package graphsTests

import graphs.edges.Edge
import graphs.graphs.AbstractGraph
import graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


abstract class AbstractGraphTests<E : Edge, G : AbstractGraph<Int, E>> {
    protected lateinit var graph: G

    @BeforeEach
    protected abstract fun setup()

    private fun checkEdgesEquals(firstEdge: E?, secondEdge: E?) {
        assertEquals(firstEdge?.verticesNumbers, secondEdge?.verticesNumbers)
    }

    protected fun checkGraphEdgesEquals(expectedEdges: MutableMap<Int, E>, graphEdges: MutableMap<Int, E>) {
        assertEquals(expectedEdges.count(), graphEdges.count())
        for (num in expectedEdges.keys) {
            checkEdgesEquals(expectedEdges[num], graphEdges[num])
        }
    }

    protected abstract fun createEdge(verticesNumbers: Pair<Int, Int>): E

    @Test
    @DisplayName("add vertex to empty graph")
    fun addVertexEmptyGraph() {
        graph.addVertex(5)
        val expectedVertices = mutableMapOf(Pair(0, Vertex(5)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("add vertex with value which is already in the graph")
    fun addVertexExistentValue() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)))
        graph.lastVertexNumber = 1
        graph.addVertex(5)
        val expectedVertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(5)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("remove existent vertex")
    fun removeExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)), Pair(2, Vertex(7)))
        graph.lastVertexNumber = 3
        assertEquals(Vertex(6), graph.removeVertex(1))
        val expectedVertices = mutableMapOf(Pair(0, Vertex(5)), Pair(2, Vertex(7)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("remove non-existent vertex")
    fun removeNonExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertNull(graph.removeVertex(2))
        val expectedVertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("get value of existent vertex")
    fun getValueExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertEquals(5, graph.getVertexValue(0))
    }

    @Test
    @DisplayName("get value of non-existent vertex")
    fun getValueNonExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertNull(graph.getVertexValue(2))
    }

    @Test
    @DisplayName("change value of existent vertex")
    fun changeValueExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertTrue(graph.changeVertexValue(0, 10))
        val expectedVertices = mutableMapOf(Pair(0, Vertex(10)), Pair(1, Vertex(6)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("change value of non-existent vertex")
    fun changeValueNonExistentVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertFalse(graph.changeVertexValue(2, 10))
        val expectedVertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        assertEquals(expectedVertices, graph.vertices)
    }

    @Test
    @DisplayName("add edge loop")
    fun addEdgeLoop() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertFalse(graph.addEdge(0, 0))
        val expectedEdges: MutableMap<Int, E> = mutableMapOf()
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("add edge with non-existent first vertex")
    fun addEdgeNonExistentFirstVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertFalse(graph.addEdge(3, 0))
        val expectedEdges: MutableMap<Int, E> = mutableMapOf()
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("add edge with non-existent second vertex")
    fun addEdgeNonExistentSecondVertex() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertFalse(graph.addEdge(0, 2))
        val expectedEdges: MutableMap<Int, E> = mutableMapOf()
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("add existent edge")
    fun addExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertFalse(graph.addEdge(0, 1))
        val expectedEdges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("add edge opposite to existent")
    protected abstract fun addEdgeOppositeExistent()

    @Test
    @DisplayName("add correct edge")
    fun addCorrectEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        assertTrue(graph.addEdge(0, 1))
        val expectedEdges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("remove existent edge")
    fun removeExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        checkEdgesEquals(createEdge(Pair(0, 1)), graph.removeEdge(0))
        val expectedEdges: MutableMap<Int, E> = mutableMapOf()
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("remove non-existent edge")
    fun removeNonExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertNull(graph.removeEdge(1))
        val expectedEdges: MutableMap<Int, E> = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        checkGraphEdgesEquals(expectedEdges, graph.edges)
    }

    @Test
    @DisplayName("get vertices numbers of existent edge")
    fun getVerticesNumbersExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        val expectedVerticesNumbers = Pair(0, 1)
        assertEquals(expectedVerticesNumbers, graph.getEdgeVerticesNumbers(0))
    }

    @Test
    @DisplayName("get vertices numbers of non-existent edge")
    fun getVerticesNumbersNonExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertNull(graph.getEdgeVerticesNumbers(1))
    }

    @Test
    @DisplayName("transform graph to its adjacency map")
    open fun transformGraphToMap() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(1)), Pair(1, Vertex(1)), Pair(2, Vertex(2)))
        graph.lastVertexNumber = 3
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))), Pair(1, createEdge(Pair(1, 2))), Pair(2, createEdge(Pair(0, 2))))
        graph.lastEdgeNumber = 3
        val expectedMap = mapOf(Pair(0, setOf(1, 2)), Pair(1, setOf(0, 2)), Pair(2, setOf(0, 1)))
        assertEquals(expectedMap, graph.toAdjacencyMap())
    }
}
