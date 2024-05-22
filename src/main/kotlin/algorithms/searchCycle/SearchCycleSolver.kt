package algorithms.searchCycle

import graphs.graphs.Graph

class SearchCycleSolver<V>(val graph: Graph<V>) { // non-negative numbers of vertices
    private val verticesAdjacencyList = graph.toAdjacencyMap()
    private val cycleVertices = mutableListOf<Int>()
    private val isVertexUsed = mutableMapOf<Int, Boolean>()

    fun searchCycle(): Boolean { // only first cycle found
        initializeUsageList()

        for (vertex in verticesAdjacencyList.keys) {
            if (isVertexUsed[vertex] != true) {
                dfs(vertex)

                if (cycleVertices.size != 0) {
                    return true
                }
            }
        }

        return cycleVertices.size != 0
    }

    fun getCycle(): List<Int> {
        val answer = cycleVertices

        if (cycleVertices.size != 0) {
            answer.reverse()
        }

        return answer.toList() // correct answer (reverse), first vertex included twice
    }

    private fun initializeUsageList() {
        for (vertex in verticesAdjacencyList.keys) {
            isVertexUsed[vertex] = false
        }
    }

    private fun dfs(currVertex: Int, prevVertex: Int = -1): Int {
        if (isVertexUsed[currVertex] == true) {
            cycleVertices.add(currVertex)
            return currVertex // beginning of cycle
        }

        isVertexUsed[currVertex] = true
        val tempAdjacencyList = verticesAdjacencyList[currVertex] ?: return -1 // adjacency list is never empty

        for (tempVertex in tempAdjacencyList) {
            if (tempVertex != prevVertex) {
                val result = dfs(tempVertex, currVertex)

                if (result != -1) {
                    cycleVertices.add(currVertex)
                    return if (result == currVertex) -1 else result // it's important for directed graphs
                }
            }
        }

        return -1
    }
}
