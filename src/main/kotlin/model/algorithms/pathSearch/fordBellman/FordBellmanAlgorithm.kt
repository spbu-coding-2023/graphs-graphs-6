package model.algorithms.pathSearch.fordBellman

import model.algorithms.pathSearch.PathResult
import model.graphs.graphs.WeightedDirectedGraph
import model.graphs.vertex.Vertex
import kotlin.Int.Companion.MAX_VALUE

class FordBellmanAlgorithm<V>(val graph: WeightedDirectedGraph<V>) {
    fun findPath(startVertexNum: Int, finishVertexNum: Int): PathResult<V>? {
        val distances: MutableMap<Int, Int> = mutableMapOf()
        val parents: MutableMap<Int, Int> = mutableMapOf()
        if ((!(graph.vertices.keys.contains(startVertexNum))) || (!(graph.vertices.keys.contains(finishVertexNum)))) return null
        for (vertexNum in graph.vertices.keys) {
            distances[vertexNum] = MAX_VALUE
            parents[vertexNum] = -1
        }
        distances[startVertexNum] = 0
        var it = 0
        do {
            var changesHappened = false
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
            }
            ++it
            if (it > graph.vertices.size) throw IllegalArgumentException("Negative cycle in the graph! Isn't supported by Ford-Bellman algorithm.")
        } while (changesHappened)

        if (distances[finishVertexNum] == MAX_VALUE) return null
        else {
            val path: MutableList<Vertex<V>> = mutableListOf()
            var currVertexNum = finishVertexNum
            while (currVertexNum != -1) {
                val currVertex = graph.vertices[currVertexNum]
                if (currVertex == null) return null
                else {
                    path.add(currVertex)
                    currVertexNum = parents[currVertexNum] ?: break
                }
            }
            return PathResult(graph, path.reversed())
        }
    }
}
