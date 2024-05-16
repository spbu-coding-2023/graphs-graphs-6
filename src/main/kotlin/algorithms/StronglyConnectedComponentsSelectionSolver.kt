package algorithms

import graphs.graphs.DirectedGraph

class StronglyConnectedComponentsSelectionSolver<V>(val graph: DirectedGraph<V>) {
    private val visited: MutableMap<Int, Boolean> = mutableMapOf()
    private val graphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    private val reversedGraphMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    private val order: MutableList<Int> = mutableListOf()
    private val components: MutableMap<Int, Int> = mutableMapOf()
    private var lastComponentNum = 1

    private fun initializeGraphs() {
        for (edge in graph.edges.values) {
            val firstVertexNum = edge.verticesNumbers.first
            val secondVertexNum = edge.verticesNumbers.second
            if (graphMap.containsKey(firstVertexNum)) graphMap[firstVertexNum]?.add(secondVertexNum)
            else graphMap[firstVertexNum] = mutableSetOf(secondVertexNum)
            if (reversedGraphMap.containsKey(secondVertexNum)) reversedGraphMap[secondVertexNum]?.add(
                firstVertexNum
            )
            else reversedGraphMap[secondVertexNum] = mutableSetOf(firstVertexNum)
        }
    }

    private fun initializeVisited() {
        for (vertexNum in graph.vertices.keys) visited[vertexNum] = false
    }

    private fun dfsOrder(vertexNum: Int) {
        visited[vertexNum] = true
        val nextVertices = graphMap[vertexNum]
        if (nextVertices != null) {
            for (nextVertexNum in nextVertices) {
                if (visited[nextVertexNum] == false) dfsOrder(nextVertexNum)
            }
        }
        order.add(vertexNum)
    }

    private fun dfsComponents(vertexNum: Int) {
        visited[vertexNum] = true
        components[vertexNum] = lastComponentNum
        val nextVertices = reversedGraphMap[vertexNum]
        if (nextVertices != null) {
            for (nextVertexNum in nextVertices) {
                if (visited[nextVertexNum] == false) dfsComponents(nextVertexNum)
            }
        }
    }

    fun selectStronglyConnectedComponents(): MutableMap<Int, Int> {
        initializeGraphs()
        initializeVisited()
        for (vertexNum in graphMap.keys) {
            if (visited[vertexNum] == false) dfsOrder(vertexNum)
        }
        initializeVisited() // we need visited[vertexNum] = false again for all vertices
        for (vertexIndex in 0 until order.size) {
            val vertexNum = order[order.size - 1 - vertexIndex]
            if (visited[vertexNum] == false) {
                dfsComponents(vertexNum)
                ++lastComponentNum
            }
        }
        return components
    }
}
