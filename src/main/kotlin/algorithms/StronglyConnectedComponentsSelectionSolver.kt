package algorithms

import graphs.graphs.DirectedGraph

class StronglyConnectedComponentsSelectionSolver<V> {
    private fun toMap(graph: DirectedGraph<V>) {
        val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        for (vertexNum in graph.vertices.keys) {
            for (edge in graph.edges.values) {
                if (edge.verticesNumbers.first == vertexNum) {
                    if (graphMap.containsKey(vertexNum)) graphMap[vertexNum]?.add(edge.verticesNumbers.second)
                    else graphMap[vertexNum] = mutableSetOf(edge.verticesNumbers.second)
                }
            }
        }
    }

    fun selectStronglyConnectedComponents(graph: DirectedGraph<V>) {
        val graphMap = toMap(graph)
    }
}