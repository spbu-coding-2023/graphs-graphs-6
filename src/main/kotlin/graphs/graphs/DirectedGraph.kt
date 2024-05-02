package graphs.graphs

import graphs.edges.Edge

class DirectedGraph<V> : Graph<V>() {
    // edges are equivalent when both source and destination are the same
    override fun checkEdgesEquivalent(firstEdge: Edge, secondEdge: Edge) =
        ((firstEdge.firstVertexNumber == secondEdge.firstVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.secondVertexNumber))
}
