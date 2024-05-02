package graphs.graphs

import graphs.edges.WeightedEdge

class WeightedDirectedGraph<V> : WeightedGraph<V>() {
    // edges are equivalent when both source and destination are the same
    override fun checkEdgesEquivalent(firstEdge: WeightedEdge, secondEdge: WeightedEdge) =
        ((firstEdge.firstVertexNumber == secondEdge.firstVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.secondVertexNumber))
}
