package graphsTests

import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

abstract class AbstractWeightedGraphTests<G : WeightedGraph<Int>> : AbstractGraphTests<WeightedEdge, G>() {
    @BeforeEach
    abstract override fun setup()

    override fun createEdge(verticesNumbers: Pair<Int, Int>) = WeightedEdge(verticesNumbers)

    @Test
    @DisplayName("add edge opposite to existent")
    abstract override fun addEdgeOppositeExistent()

    @Test
    @DisplayName("set weight of just added edge")
    fun setWeightNewEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.addEdge(0, 1)
        assertTrue(graph.changeEdgeWeight(0, 5))
        assertEquals(5, graph.getEdgeWeight(0))
    }

    @Test
    @DisplayName("change weight of existent edge")
    fun changeWeightExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        graph.edges[0]?.weight = 5
        assertTrue(graph.changeEdgeWeight(0, 10))
        assertEquals(10, graph.getEdgeWeight(0))
    }

    @Test
    @DisplayName("change value of non-existent edge")
    fun changeWeightNonExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        assertFalse(graph.changeEdgeWeight(1, 10))
    }

    @Test
    @DisplayName("get weight of just added edge")
    fun getWeightNewEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.addEdge(0, 1)
        assertEquals(0, graph.getEdgeWeight(0))
    }

    @Test
    @DisplayName("get weight of existent edge")
    fun getWeightExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        graph.edges[0]?.weight = 5
        assertEquals(5, graph.getEdgeWeight(0))
    }

    @Test
    @DisplayName("get weight of non-existent edge")
    fun getWeightNonExistentEdge() {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, createEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        graph.edges[0]?.weight = 5
        assertNull(graph.getEdgeWeight(1))
    }
}
