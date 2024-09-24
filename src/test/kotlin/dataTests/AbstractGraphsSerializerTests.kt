package dataTests

import dataTests.stubs.IntVertexDataSerializer
import dataTests.stubs.StubGraphDeserializer
import dataTests.stubs.StubGraphSerializer
import model.data.GraphRepresentation
import model.graphs.edges.Edge
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.AbstractGraph
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

open class AbstractGraphsSerializerTests {
    lateinit var weightedGraph: WeightedGraph<Int>
    lateinit var ordinaryGraph: Graph<Int>
    lateinit var directedGraph: DirectedGraph<Int>
    lateinit var weightedDirectedGraph: WeightedDirectedGraph<Int>

    @BeforeEach
    open fun setup() {
        weightedGraph = WeightedGraph()
        initWeightedGraph(weightedGraph)
        ordinaryGraph = Graph<Int>()
        initOrdinaryGraph(ordinaryGraph)
        directedGraph = DirectedGraph()
        initDirectedGraph(directedGraph)
        weightedDirectedGraph = WeightedDirectedGraph()
        initWeightedDirectedGraph(weightedDirectedGraph)
    }

    private fun initWeightedDirectedGraph(graph: WeightedDirectedGraph<Int>) {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, WeightedEdge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
        graph.changeEdgeWeight(0, 156)
    }
    private fun initDirectedGraph(graph: DirectedGraph<Int>) {
        graph.vertices = mutableMapOf(Pair(0, Vertex(5)), Pair(1, Vertex(6)))
        graph.lastVertexNumber = 2
        graph.edges = mutableMapOf(Pair(0, Edge(Pair(0, 1))))
        graph.lastEdgeNumber = 1
    }
    private fun initOrdinaryGraph(graph: Graph<Int>) {
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)

        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 4)
        graph.addEdge(2, 4)
        graph.addEdge(3, 2)
        graph.addEdge(3, 4)
    }
    private fun initWeightedGraph(graph: WeightedGraph<Int>) {
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

    private fun assertGraphRepresentation(expected: GraphRepresentation, actual: GraphRepresentation) {
        assertEquals(expected.name, actual.name)
        assertEquals(expected.vertexValueTypeName, actual.vertexValueTypeName)
        assertContentEquals(expected.vertexValuesList, actual.vertexValuesList)
        assertContentEquals(expected.edges, actual.edges)
        assertEquals(expected.isDirected, actual.isDirected)
        assertEquals(expected.isWeighted, actual.isWeighted)
        assertContentEquals(expected.weightEdgeList, actual.weightEdgeList)
    }

    private fun <V, T : Edge> assertGraph(expected: AbstractGraph<V, T>, actual: AbstractGraph<V, T>) {
        for ((index, edge) in expected.edges) {
            val actualKey = actual.edges.keys.toList()[index]
            val actualEdge = actual.edges[actualKey]
            if (actualEdge == null) throw Error("Edge with index $index not found")
            assertEquals(edge.verticesNumbers, actualEdge.verticesNumbers)
            if (expected is WeightedGraph) {
                assertEquals((edge as WeightedEdge).weight, (actualEdge as WeightedEdge).weight)
            }
        }

        for ((index, vertex) in expected.vertices) {
            val actualKey = actual.vertices.keys.toList()[index]
            val actualVertex = actual.vertices[actualKey]
            if (actualVertex == null) throw Error("Vertex with index $index not found")
            assertEquals(vertex.value, actualVertex.value)
        }

        val expectedAdjacencyMap = expected.toAdjacencyMap()
        val actualAdjacencyMap = actual.toAdjacencyMap()

        expectedAdjacencyMap.keys.toList()
        for (exKey in expectedAdjacencyMap.keys) {
            assertTrue(actualAdjacencyMap.containsKey(exKey))
            val actualVal = actualAdjacencyMap[exKey]
            assertNotNull(actualVal)
            assertContentEquals(expectedAdjacencyMap[exKey]!!.toList(), actualVal.toList())
        }
    }

    @Test
    @DisplayName("Weighted directed graph deserialization")
    fun weightedDirectedGraphDeserialize() {
        val graphDeserializer = StubGraphDeserializer()
        val representation = graphDeserializer.deserializeGraph("weighteddirected")

        val result = representation.createDirectedWeightedGraphByRepresentation(IntVertexDataSerializer())

        assertGraph(weightedDirectedGraph, result)
    }

    @Test
    @DisplayName("Weighted directed graph serialization")
    fun weightedDirectedGraphSerialize() {
        val graphSerializer = StubGraphSerializer()
        graphSerializer.serializeNotOrdinaryGraph("test", weightedDirectedGraph, IntVertexDataSerializer())
        val resultGraphRepresentation = graphSerializer.lastGraphRepresentation
        assertNotNull(resultGraphRepresentation)
        val expectedGraphRepresentation = GraphRepresentation(
            "test",
            "int",
            listOf("5", "6"),
            listOf(Pair(0, 1)),
            true,
            true,
            listOf(156)
        )
        assertGraphRepresentation(expectedGraphRepresentation, resultGraphRepresentation)
    }

    @Test
    @DisplayName("Directed graph serialization")
    fun directedGraphSerialize() {
        val graphSerializer = StubGraphSerializer()
        graphSerializer.serializeNotOrdinaryGraph("test", directedGraph, IntVertexDataSerializer())
        val resultGraphRepresentation = graphSerializer.lastGraphRepresentation
        assertNotNull(resultGraphRepresentation)
        val expectedGraphRepresentation = GraphRepresentation(
            "test",
            "int",
            listOf("5", "6"),
            listOf(Pair(0, 1)),
            true,
            false,
            listOf()
        )
        assertGraphRepresentation(expectedGraphRepresentation, resultGraphRepresentation)
    }

    @Test
    @DisplayName("Directed graph deserialization")
    fun directedGraphDeserialize() {
        val graphDeserializer = StubGraphDeserializer()
        val representation = graphDeserializer.deserializeGraph("directed")

        val result = representation.createDirectedGraphByRepresentation(IntVertexDataSerializer())

        assertGraph(directedGraph, result)
    }

    @Test
    @DisplayName("Ordinary graph serialization")
    fun ordinaryGraphSerialize() {
        val graphSerializer = StubGraphSerializer()
        graphSerializer.serializeOrdinaryGraph("test", ordinaryGraph, IntVertexDataSerializer())
        val resultGraphRepresentation = graphSerializer.lastGraphRepresentation
        assertNotNull(resultGraphRepresentation)
        val expectedGraphRepresentation = GraphRepresentation(
            "test",
            "int",
            listOf("0", "1", "2", "3", "4"),
            listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3), Pair(1, 4), Pair(2, 4), Pair(3, 2), Pair(3, 4)),
            false,
            false,
            listOf()
        )

        assertGraphRepresentation(expectedGraphRepresentation, resultGraphRepresentation)
    }

    @Test
    @DisplayName("Ordinary graph deserialization")
    fun ordinaryGraphDeserialize() {
        val graphDeserializer = StubGraphDeserializer()
        val representation = graphDeserializer.deserializeGraph("ordinary")

        val result = representation.createOrdinaryGraphByRepresentation(IntVertexDataSerializer())

        assertGraph(ordinaryGraph, result)
    }

    @Test
    @DisplayName("Weighted graph serialization")
    fun weightedGraphSerialize() {
        val graphSerializer = StubGraphSerializer()
        graphSerializer.serializeNotOrdinaryGraph("test", weightedGraph, IntVertexDataSerializer())
        val resultGraphRepresentation = graphSerializer.lastGraphRepresentation
        assertNotNull(resultGraphRepresentation)
        val expectedGraphRepresentation = GraphRepresentation(
            "test",
            "int",
            listOf("0", "1", "2", "3", "4"),
            listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3), Pair(1, 4), Pair(2, 4), Pair(3, 2), Pair(3, 4)),
            false,
            true,
            listOf(3, 6, 1, 8, 4, 4, 12)
        )

        assertGraphRepresentation(expectedGraphRepresentation, resultGraphRepresentation)
    }

    @Test
    @DisplayName("Weighted graph deserialization")
    fun weightedGraphDeserialize() {
        val graphDeserializer = StubGraphDeserializer()
        val representation = graphDeserializer.deserializeGraph("weighted")

        val result = representation.createWeightedGraphByRepresentation(IntVertexDataSerializer())

        assertGraph(weightedGraph, result)
    }
}