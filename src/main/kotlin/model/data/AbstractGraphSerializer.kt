package model.data

import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph
import model.graphs.graphs.DirectedGraph
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.graphs.WeightedGraph

abstract class AbstractGraphSerializer {

    abstract fun deserializeGraph(name: String): GraphRepresentation
    protected abstract fun serializeGraphRepresentation(graph: GraphRepresentation)

    protected abstract fun getNameForNextGraph(): String

    abstract fun isGraphAlreadyStored(name: String): Boolean

    private fun <V, T : Edge> initGraphSerialization(graphName: String?, graph: AbstractGraph<V, T>, vertexDataSerializer: IVertexDataSerializer<V>): GraphRepresentationBuilder<V, T> {
        var name = ""
        name = graphName ?: getNameForNextGraph()

        if (isGraphAlreadyStored(name)) throw Exception(DataConstants.ALREADYEXIST_ERROR)

        return GraphRepresentationBuilder<V, T>(vertexDataSerializer.getSerializableType(), name, graph, vertexDataSerializer)
    }
    fun <V> serializeOrdinaryGraph(graphName: String?, graph: AbstractGraph<V, Edge>, vertexDataSerializer: IVertexDataSerializer<V>) = serializeGraphRepresentation(initGraphSerialization(graphName, graph, vertexDataSerializer).build())

    fun <V> serializeNotOrdinaryGraph(graphName: String?, graph: DirectedGraph<V>, vertexDataSerializer: IVertexDataSerializer<V>) {
        val initResult = initGraphSerialization(graphName, graph, vertexDataSerializer)
        initResult.makeDirected()
        serializeGraphRepresentation(initResult.build())
    }
    fun <V> serializeNotOrdinaryGraph(graphName: String?, graph: WeightedGraph<V>, vertexDataSerializer: IVertexDataSerializer<V>) {
        val initResult = initGraphSerialization(graphName, graph, vertexDataSerializer)
        initResult.makeWeighted()
        serializeGraphRepresentation(initResult.build())
    }
    fun <V> serializeNotOrdinaryGraph(graphName: String?, graph: WeightedDirectedGraph<V>, vertexDataSerializer: IVertexDataSerializer<V>) {
        val initResult = initGraphSerialization(graphName, graph, vertexDataSerializer)
        initResult.makeWeighted()
        initResult.makeDirected()
        return serializeGraphRepresentation(initResult.build())
    }
}