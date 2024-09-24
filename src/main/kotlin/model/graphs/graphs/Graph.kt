package model.graphs.graphs

import model.graphs.edges.Edge

open class Graph<V> : AbstractGraph<V, Edge>() {
    override fun createEdge(firstVertexNumber: Int, secondVertexNumber: Int): Edge =
        Edge(Pair(firstVertexNumber, secondVertexNumber))
}
