package algorithms

import graphs.graphs.Graph

class SearchCycleSolver<V>(val graph: Graph<V>) { // non-directed graph, non-negative numbers of vertices
    private class VertexValue {
        var isUsed = false
        val adjacencyVertices: MutableSet<Int> = mutableSetOf()
    }

    private val verticesAdjacencyList = mutableMapOf<Int, VertexValue>()
    private val cycleVertices = mutableListOf<Int>()

    fun searchCycle(): Boolean { // only first cycle showed
        initializeAdjacencyList()

        for (vertex in verticesAdjacencyList.keys) {
            if (verticesAdjacencyList[vertex]?.isUsed != true) {
                dfs(vertex)

                if (cycleVertices.size != 0) {
                    return true
                }
            }
        }

        return cycleVertices.size == 0
    }

    // TODO: getter test check
    fun getCycle(): MutableList<Int>? {
        if (cycleVertices.size == 0) {
            return null
        } else {
            val answer = cycleVertices // TODO: copy check (same answer two times)
            answer.reverse()

            return answer // correct answer (reverse), first vertex included twice
        }
    }

    private fun initializeAdjacencyList() {
        for (edge in graph.edges) {
            val firstVertex = edge.value.verticesNumbers.first
            val secondVertex = edge.value.verticesNumbers.second

            if (firstVertex !in verticesAdjacencyList) {
                verticesAdjacencyList[firstVertex] = VertexValue()
            }

            if (secondVertex !in verticesAdjacencyList) {
                verticesAdjacencyList[secondVertex] = VertexValue()
            }

            verticesAdjacencyList[firstVertex]?.adjacencyVertices?.add(secondVertex)
            verticesAdjacencyList[secondVertex]?.adjacencyVertices?.add(firstVertex) // TODO: case of directed graph??
        }

        for (vertex in graph.vertices) { // non linked vertices
            if (vertex.key !in verticesAdjacencyList.keys) {
                verticesAdjacencyList[vertex.key] = VertexValue()
            }
        }
    }

    // TODO: should work for weighted graphs (add more tests)

    private fun dfs(currVertex: Int, prevVertex: Int = -1): Int {
        if (verticesAdjacencyList[currVertex]?.isUsed == true) {
            cycleVertices.add(currVertex)
            return currVertex // beginning of cycle
        }

        verticesAdjacencyList[currVertex]?.isUsed = true
        val tempAdjacencyList =
            verticesAdjacencyList[currVertex]?.adjacencyVertices ?: return -1 // adjacency list is never empty

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
