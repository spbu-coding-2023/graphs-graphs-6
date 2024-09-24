package model.graphs.graphs

import model.graphs.edges.WeightedEdge

open class WeightedGraph<V> : AbstractGraph<V, WeightedEdge>() {
    override fun createEdge(firstVertexNumber: Int, secondVertexNumber: Int): WeightedEdge =
        WeightedEdge(Pair(firstVertexNumber, secondVertexNumber))

    fun changeEdgeWeight(edgeNumber: Int, newWeight: Int): Boolean {
        if (edges[edgeNumber] == null) return false
        edges[edgeNumber]?.weight = newWeight
        return true
    }

    fun getEdgeWeight(edgeNumber: Int): Int? = edges[edgeNumber]?.weight
}
