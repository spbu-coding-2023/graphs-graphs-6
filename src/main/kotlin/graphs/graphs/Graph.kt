package graphs.graphs

import graphs.edges.Edge

open class Graph<V> : AbstractGraph<V, Edge>() {
    override fun createEdge(firstVertexNumber: Int, secondVertexNumber: Int): Edge =
        Edge(firstVertexNumber, secondVertexNumber)

}
