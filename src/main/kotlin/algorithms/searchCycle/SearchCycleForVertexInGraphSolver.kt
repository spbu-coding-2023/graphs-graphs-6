package algorithms.searchCycle

import graphs.graphs.Graph

open class SearchCycleForVertexInGraphSolver<V>(val graph: Graph<V>) { // non-negative numbers of vertices
    protected enum class Color {
        White,
        Gray,
        Black
    }

    private val verticesAdjacencyList = graph.toAdjacencyMap()
    private val cycleVertices = mutableListOf<Int>()
    private val vertexColor = mutableMapOf<Int, Color>()

    fun searchCycleForVertex(vertex: Int): Boolean { // only first cycle found
        if (verticesAdjacencyList.isEmpty()) {
            return false
        }

        initializeUsageList()

        val result = dfs(vertex)
        return result == -2
    }

    fun getCycle(): List<Int> {
        val answer = cycleVertices

        if (cycleVertices.size > 0) {
            answer.reverse()
        }

        return answer.toList() // correct answer (reverse), first vertex included twice; if algorithm's result is false, empty list returned
    }

    private fun initializeUsageList() {
        for (vertex in verticesAdjacencyList.keys) {
            vertexColor[vertex] = Color.White
        }
    }

    protected open fun parentsCheck(currVertex: Int, parentVertex: Int): Boolean {
        return currVertex == parentVertex
    }

    private fun dfs(currVertex: Int, prevVertex: Int = -1): Int {
        if (vertexColor[currVertex] == Color.Gray) { // cycle of two in directed graphs
            cycleVertices.add(currVertex)
            return currVertex
        }
        vertexColor[currVertex] = Color.Gray

        val tempAdjacencyList = verticesAdjacencyList[currVertex] ?: return -1 // adjacency list is never empty
        for (tempVertex in tempAdjacencyList) {
            if (parentsCheck(tempVertex, prevVertex)) {
                continue
            }

            if (vertexColor[tempVertex] == Color.White) {
                val result = dfs(tempVertex, currVertex)

                if (result >= 0) {
                    cycleVertices.add(currVertex)

                    vertexColor[currVertex] = Color.Black
                    return if (result == currVertex) -2 else result
                }
            }

            if (vertexColor[tempVertex] == Color.Gray) {
                cycleVertices.add(tempVertex)
                cycleVertices.add(currVertex)

                vertexColor[currVertex] = Color.Black
                return tempVertex
            }
        }

        vertexColor[currVertex] = Color.Black
        return -1
    }
}
