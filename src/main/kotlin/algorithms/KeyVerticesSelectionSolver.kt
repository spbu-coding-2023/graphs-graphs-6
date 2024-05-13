package algorithms

import graphs.graphs.Graph

class KeyVerticesSelectionSolver<V>(val graph: Graph<V>) {
    private val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    private val randomTransitionProbability = 0.15 / graph.vertices.size
    private val stochasticMatrix: MutableMap<Int, MutableMap<Int, Double>> = mutableMapOf()

    private fun initializeGraphMap() {
        for (edge in graph.edges.values) {
            val firstVertexNum = edge.verticesNumbers.first
            val secondVertexNum = edge.verticesNumbers.second
            if (graphMap.containsKey(firstVertexNum)) graphMap[firstVertexNum]?.add(secondVertexNum)
            else graphMap[firstVertexNum] = mutableSetOf(secondVertexNum)
            if (graphMap.containsKey(secondVertexNum)) graphMap[secondVertexNum]?.add(
                firstVertexNum
            )
            else graphMap[secondVertexNum] = mutableSetOf(firstVertexNum)
        }
    }

    private fun initializeStochasticMatrix() {

    }

    fun selectKeyVertices() {
        initializeGraphMap()
        initializeStochasticMatrix()
    }
}
