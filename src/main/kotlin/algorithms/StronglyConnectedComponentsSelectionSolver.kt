package algorithms

import graphs.graphs.DirectedGraph

class StronglyConnectedComponentsSelectionSolver<V> {
    private val visited: MutableMap<Int, Boolean> = mutableMapOf()

    private fun initializeGraphs(
        graph: DirectedGraph<V>,
        graphMap: MutableMap<Int, MutableSet<Int>>,
        reversedGraphMap: MutableMap<Int, MutableSet<Int>>
    ) {
        for (vertexNum in graph.vertices.keys) {
            for (edge in graph.edges.values) {
                if (edge.verticesNumbers.first == vertexNum) {
                    if (graphMap.containsKey(vertexNum)) graphMap[vertexNum]?.add(edge.verticesNumbers.second)
                    else graphMap[vertexNum] = mutableSetOf(edge.verticesNumbers.second)
                    if (reversedGraphMap.containsKey(edge.verticesNumbers.second)) reversedGraphMap[edge.verticesNumbers.second]?.add(
                        vertexNum
                    )
                    else reversedGraphMap[edge.verticesNumbers.second] = mutableSetOf(vertexNum)
                }
            }
        }
    }

    private fun initializeVisited(graph: DirectedGraph<V>) {
        for (vertexNum in graph.vertices.keys) visited[vertexNum] = false
    }

    fun selectStronglyConnectedComponents(graph: DirectedGraph<V>) {
        val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        val reversedGraphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        initializeGraphs(graph, graphMap, reversedGraphMap)
        initializeVisited(graph)
        
    }
}