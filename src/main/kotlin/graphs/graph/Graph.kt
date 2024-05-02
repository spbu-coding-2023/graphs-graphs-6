package graphs.graph

open class Graph<V> {
    protected val vertices: MutableMap<Int, Vertex<V>> = mutableMapOf()
    protected val edges: MutableMap<Int, Edge> = mutableMapOf()
    internal var lastVertexNumber = 0
    internal var lastEdgeNumber = 0

    // size of list "vertices" is index of our next vertex, same for edge further
    fun addVertex(value: V) {
        vertices[lastVertexNumber++] = Vertex(value)
    }

    open fun checkEdgesEquivalent(firstEdge: Edge, secondEdge: Edge): Boolean = (((firstEdge.firstVertexNumber == secondEdge.firstVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.secondVertexNumber)) || ((firstEdge.firstVertexNumber == secondEdge.secondVertexNumber) && (firstEdge.secondVertexNumber == secondEdge.firstVertexNumber)))

    fun addEdge(firstVertexNumber: Int, secondVertexNumber: Int): Boolean {
        // if edge is a loop, we don't add it
        if (firstVertexNumber == secondVertexNumber) return false
        // if edge's vertex is not in graph, we don't add it
        if ((!(vertices.containsKey(firstVertexNumber))) || (!(vertices.containsKey(secondVertexNumber)))) return false
        val edge = Edge(firstVertexNumber, secondVertexNumber)
        for (currEdge in edges.values) {
            // if edge is already in the graph
            if (checkEdgesEquivalent(edge, currEdge)) return false
        }
        edges[lastEdgeNumber++] = edge
        return true
    }

    fun removeVertex(vertexNumber: Int): Vertex<V>? = vertices.remove(vertexNumber)
    fun removeEdge(edgeNumber: Int): Edge? = edges.remove(edgeNumber)
    fun getVertexValue(vertexNumber: Int): V? = vertices[vertexNumber]?.value
    fun getEdgeVertices(edgeNumber: Int): Pair<Int?, Int?> =
        Pair(edges[edgeNumber]?.firstVertexNumber, edges[edgeNumber]?.secondVertexNumber)

    fun changeVertexValue(vertexNumber: Int, newValue: V) {
        vertices[vertexNumber]?.value = newValue
    }
}