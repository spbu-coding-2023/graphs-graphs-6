package graphs.graphs

import graphs.edges.WeightedEdge

open class WeightedGraph<V> : AbstractGraph<V, WeightedEdge>() {
    override fun createEdge(firstVertexNumber: Int, secondVertexNumber: Int): WeightedEdge =
        WeightedEdge(firstVertexNumber, secondVertexNumber)

    fun getEdgeWeight(edgeNumber: Int): Int? = edges[edgeNumber]?.weight

    fun changeEdgeWeight(edgeNumber: Int, newWeight: Int) {
        edges[edgeNumber]?.weight = newWeight
    }
}