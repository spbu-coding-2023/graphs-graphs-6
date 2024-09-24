package model.data

import kotlinx.serialization.Serializable
import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.Graph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph
import java.lang.reflect.Type

@Serializable
data class GraphRepresentation(val name: String, val vertexValueTypeName: String, val vertexValuesList: List<String>, val edges: List<Pair<Int, Int>>, val isDirected: Boolean, val isWeighted: Boolean, val weightEdgeList: List<Int>) {

    private fun <V, E : Edge> initGraphCreation(graph: AbstractGraph<V, E>, deserializer: IVertexDataSerializer<V>) {
        for (i in vertexValuesList) {
            val value = deserializer.deserialize(i)
            graph.addVertex(value)
        }

        for (i in edges) {
            graph.addEdge(i.first, i.second)
        }
    }

    fun <V> createOrdinaryGraphByRepresentation(deserializer: IVertexDataSerializer<V>): Graph<V> {
        val result = Graph<V>()
        initGraphCreation(result, deserializer)
        return result
    }
    fun <V> createDirectedWeightedGraphByRepresentation(deserializer: IVertexDataSerializer<V>): WeightedDirectedGraph<V> {
        val result = WeightedDirectedGraph<V>()
        initGraphCreation(result, deserializer)
        for ((index, value) in weightEdgeList.withIndex()) {
            result.changeEdgeWeight(index, value)
        }
        return result
    }
    fun <V> createDirectedGraphByRepresentation(deserializer: IVertexDataSerializer<V>): DirectedGraph<V> {
        val result = DirectedGraph<V>()
        initGraphCreation(result, deserializer)
        return result
    }
    fun <V> createWeightedGraphByRepresentation(deserializer: IVertexDataSerializer<V>): WeightedGraph<V> {
        val result = WeightedGraph<V>()
        initGraphCreation(result, deserializer)
        for ((index, value) in weightEdgeList.withIndex()) {
            result.changeEdgeWeight(index, value)
        }
        return result
    }
}

class GraphRepresentationBuilder<V, T : Edge>(vertexDataType: Type, val name: String, val graph: AbstractGraph<V, T>, vertexDataSerializer: IVertexDataSerializer<V>) {
    private val vertexValueTypeName: String = vertexDataType.typeName
    private val vertexValuesList: List<String> = graph.vertices.values.map { x -> vertexDataSerializer.serialize(x.value) }.toList()
    private val edges = graph.edges.map { x -> x.value.verticesNumbers }
    private var isDirected = false
    private var isWeighted = false
    private var weightEdgeList = listOf<Int>()

    fun makeDirected(): GraphRepresentationBuilder<V, T> {
        isDirected = true
        return this
    }

    fun makeWeighted(): GraphRepresentationBuilder<V, T> {
        isWeighted = true
        weightEdgeList = (graph as WeightedGraph<V>).edges.values.map { x -> x.weight }
        return this
    }

    fun build(): GraphRepresentation {
        return GraphRepresentation(name, vertexValueTypeName, vertexValuesList, edges, isDirected, isWeighted, weightEdgeList)
    }
}