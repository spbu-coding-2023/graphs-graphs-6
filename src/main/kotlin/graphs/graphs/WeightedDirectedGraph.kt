package graphs.graphs

import graphs.edges.WeightedEdge

class WeightedDirectedGraph<V> : WeightedGraph<V>() {
    override fun checkEdgesEquivalent(firstEdge: WeightedEdge, secondEdge: WeightedEdge) =
        ((firstEdge.firstVertexNumber == secondEdge.firstVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.secondVertexNumber))
}
