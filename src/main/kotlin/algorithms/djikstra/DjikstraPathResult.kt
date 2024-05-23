package algorithms.djikstra

import graphs.graphs.WeightedGraph
import graphs.vertex.Vertex

open class DjikstraPathResult<V>(val graph: WeightedGraph<V>, val vertexList: List<Vertex<V>>) {
    val sourceVertex = if (vertexList.isEmpty()) null else vertexList.first()
    val endVertex = if (vertexList.isEmpty()) null else vertexList.last()
}