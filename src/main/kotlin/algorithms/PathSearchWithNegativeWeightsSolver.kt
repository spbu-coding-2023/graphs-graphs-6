package algorithms

import graphs.graphs.WeightedDirectedGraph
import kotlin.Int.Companion.MAX_VALUE

class PathSearchWithNegativeWeightsSolver<V>(val graph: WeightedDirectedGraph<V>) {
    fun findPath(startVertexNum: Int, finishVertexNum: Int): List<Int>? {
        val distances: MutableMap<Int, Int> = mutableMapOf()
        val parents: MutableMap<Int, Int> = mutableMapOf()
        for (vertexNum in graph.vertices.keys) {
            distances[vertexNum] = MAX_VALUE
            parents[vertexNum] = -1
        }
        distances[startVertexNum] = 0
        var changesHappened = false
        var it = 0
        do {
            for (edge in graph.edges.values) {
                val distanceFirst = distances[edge.verticesNumbers.first]
                val distanceSecond = distances[edge.verticesNumbers.second]
                if (distanceFirst != null && distanceSecond != null) {
                    if (distanceFirst < MAX_VALUE) {
                        if (distanceSecond > distanceFirst + edge.weight) {
                            distances[edge.verticesNumbers.second] = distanceFirst + edge.weight
                            parents[edge.verticesNumbers.second] = edge.verticesNumbers.first
                            changesHappened = true
                        }
                    }
                }
                ++it
                if (it == graph.vertices.size) throw IllegalArgumentException("Negative cycle in the graph! Isn't supported by Ford-Bellman algorithm.")
            }
        } while (changesHappened)

        if (distances[finishVertexNum] == MAX_VALUE) return null
        else {
            val path: MutableList<Int> = mutableListOf()
            var currVertexNum = finishVertexNum
            while (currVertexNum != -1) {
                path.add(currVertexNum)
                currVertexNum = parents[currVertexNum] ?: break
            }
            return path.reversed()
        }
    }
}