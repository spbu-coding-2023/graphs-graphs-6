package graphs.graphs

import graphs.edges.WeightedEdge

class WeightedDirectedGraph<V> : WeightedGraph<V>() {
    override fun toAdjacencyMap(): Map<Int, MutableSet<Int>> {
        val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        for (edge in edges.values) {
            val firstVertexNum = edge.verticesNumbers.first
            val secondVertexNum = edge.verticesNumbers.second
            // we add only 1-2 in directed case
            if (graphMap.containsKey(firstVertexNum)) graphMap[firstVertexNum]?.add(secondVertexNum)
            else graphMap[firstVertexNum] = mutableSetOf(secondVertexNum)
        }
        for (vertexNum in vertices.keys) {
            if (!(graphMap.containsKey(vertexNum))) graphMap[vertexNum] = mutableSetOf()
        }
        return graphMap.toMap()
    }

    // edges are equivalent when both source and destination are the same
    override fun checkEdgesEquivalent(firstEdge: WeightedEdge, secondEdge: WeightedEdge) =
        (firstEdge.verticesNumbers == secondEdge.verticesNumbers)
}
