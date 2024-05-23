package model.algorithms.djikstra

import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex

open class DjikstraPathResult<V>(val graph: WeightedGraph<V>, val vertexList: List<Vertex<V>>) {
    val sourceVertex = if (vertexList.isEmpty()) null else vertexList.first()
    val endVertex = if (vertexList.isEmpty()) null else vertexList.last()
}