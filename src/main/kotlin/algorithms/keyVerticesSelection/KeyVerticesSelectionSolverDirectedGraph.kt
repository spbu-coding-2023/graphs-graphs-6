package algorithms.keyVerticesSelection

import graphs.graphs.DirectedGraph

class KeyVerticesSelectionSolverDirectedGraph<V>(override val graph: DirectedGraph<V>) :
    KeyVerticesSelectionSolverSimpleGraph<V>(graph) {
    override fun initializeGraphMap() {
        for (edge in graph.edges.values) {
            val firstVertexNum = edge.verticesNumbers.first
            val secondVertexNum = edge.verticesNumbers.second
            if (graphMap.containsKey(firstVertexNum)) graphMap[firstVertexNum]?.add(secondVertexNum)
            else graphMap[firstVertexNum] = mutableSetOf(secondVertexNum)
        }
    }
}