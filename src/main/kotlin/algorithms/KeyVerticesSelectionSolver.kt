package algorithms

import graphs.graphs.Graph

class KeyVerticesSelectionSolver<V>(val graph: Graph<V>) {
    private val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    private val randomTransitionProbability = 0.15 / graph.vertices.size
    private val stochasticMatrix: MutableMap<Int, MutableMap<Int, Double>> = mutableMapOf()

    private fun initializeGraphMap() {
        for (vertexNum in graph.vertices.keys) {
            for (edge in graph.edges.values) {
                if ((edge.verticesNumbers.first == vertexNum) || (edge.verticesNumbers.second == vertexNum)) {
                    if (graphMap.containsKey(vertexNum)) graphMap[vertexNum]?.add(edge.verticesNumbers.second)
                    else graphMap[vertexNum] = mutableSetOf(edge.verticesNumbers.second)
                    if (graphMap.containsKey(vertexNum)) graphMap[vertexNum]?.add(edge.verticesNumbers.second)
                    else graphMap[vertexNum] = mutableSetOf(edge.verticesNumbers.second)
                }
            }
        }
    }

    private fun initializeStochasticMatrix() {

    }

    fun selectKeyVertices() {
        initializeGraphMap()
        initializeStochasticMatrix()
    }
}
