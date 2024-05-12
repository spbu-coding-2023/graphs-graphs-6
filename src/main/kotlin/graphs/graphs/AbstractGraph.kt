package graphs.graphs

import graphs.edges.Edge
import graphs.vertex.Vertex

abstract class AbstractGraph<V, E : Edge> {
    internal var vertices: MutableMap<Int, Vertex<V>> = mutableMapOf()
    internal open var edges: MutableMap<Int, E> = mutableMapOf()
    internal var lastVertexNumber = 0
    internal var lastEdgeNumber = 0

    // size of list "vertices" is index of our next vertex, same for edge further
    fun addVertex(value: V) {
        vertices[lastVertexNumber++] = Vertex(value)
    }

    fun removeVertex(vertexNumber: Int): Vertex<V>? = vertices.remove(vertexNumber)

    fun getVertexValue(vertexNumber: Int): V? = vertices[vertexNumber]?.value

    fun changeVertexValue(vertexNumber: Int, newValue: V): Boolean {
        if (vertices[vertexNumber] == null) return false
        vertices[vertexNumber]?.value = newValue
        return true
    }

    // edges are equivalent if their vertices are the same (direction doesn't make effect in general case)
    protected open fun checkEdgesEquivalent(firstEdge: E, secondEdge: E): Boolean =
        (
            (firstEdge.verticesNumbers == secondEdge.verticesNumbers) || (
                firstEdge.verticesNumbers == Pair(
                    secondEdge.verticesNumbers.second,
                    secondEdge.verticesNumbers.first
                )
                )
            )

    protected abstract fun createEdge(firstVertexNumber: Int, secondVertexNumber: Int): E

    fun addEdge(firstVertexNumber: Int, secondVertexNumber: Int): Boolean {
        // if edge is a loop, we don't add it
        if (firstVertexNumber == secondVertexNumber) return false
        // if edge's vertex is not in graph, we don't add it
        if ((!(vertices.containsKey(firstVertexNumber))) || (!(vertices.containsKey(secondVertexNumber)))) return false
        val edge = createEdge(firstVertexNumber, secondVertexNumber)
        for (currEdge in edges.values) {
            // if edge is already in the graph, we don't add it
            if (checkEdgesEquivalent(edge, currEdge)) return false
        }
        edges[lastEdgeNumber++] = edge
        return true
    }

    fun removeEdge(edgeNumber: Int): E? = edges.remove(edgeNumber)

    fun getEdgeVerticesNumbers(edgeNumber: Int): Pair<Int, Int>? =
        edges[edgeNumber]?.verticesNumbers
}
