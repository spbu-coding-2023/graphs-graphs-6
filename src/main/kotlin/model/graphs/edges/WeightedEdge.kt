package model.graphs.edges

class WeightedEdge(verticesNumbers: Pair<Int, Int>) : Edge(verticesNumbers) {
    internal var weight: Int = 0
}
