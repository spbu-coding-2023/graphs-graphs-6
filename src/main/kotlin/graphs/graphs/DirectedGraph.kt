package graphs.graphs

import graphs.edges.Edge

class DirectedGraph<V> : Graph<V>() {
    override fun checkEdgesEquivalent(firstEdge: Edge, secondEdge: Edge) =
        ((firstEdge.firstVertexNumber == secondEdge.firstVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.secondVertexNumber))
}