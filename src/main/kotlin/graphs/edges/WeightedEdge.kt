package graphs.edges

class WeightedEdge(firstVertexNumber: Int, secondVertexNumber: Int) : Edge(firstVertexNumber, secondVertexNumber) {
    internal var weight: Int = 0
}
