package model.algorithms.pathSearch.djikstra

import model.algorithms.pathSearch.PathResult
import model.graphs.graphs.WeightedGraph
import model.graphs.vertex.Vertex

open class DjikstraAlgorithm<V>(protected val graph: WeightedGraph<V>) {
    fun findNearestPath(startVertex: Vertex<V>, endVertex: Vertex<V>): PathResult<V> {
        if (!graph.vertices.containsValue(startVertex) || !graph.vertices.containsValue(endVertex)) throw IllegalArgumentException()

        val startVertexId = graph.vertices.keys.first { x -> graph.vertices[x] == startVertex }
        val endVertexId = graph.vertices.keys.first { x -> graph.vertices[x] == endVertex }

        return findNearestPath(startVertexId, endVertexId)
    }
    fun findNearestPath(startVertexId: Int, endVertexId: Int): PathResult<V> {
        if (graph.vertices.isEmpty() || !graph.vertices.containsKey(startVertexId) || !graph.vertices.containsKey(endVertexId)) throw IllegalArgumentException()

        val verticesDistance = mutableMapOf<Int, Int>()
        val verticlesFrom = mutableMapOf<Int, Int?>()
        val verticlesVisited = mutableMapOf<Int, Boolean>()
        for (i in graph.vertices.keys) {
            verticesDistance[i] = Int.MAX_VALUE
            verticlesFrom[i] = null
            verticlesVisited[i] = false
        }
        verticesDistance[startVertexId] = 0
        for (i in graph.vertices.keys) {
            var nearestId: Int? = null

            for (j in graph.vertices.keys) {
                if (!verticlesVisited.getOrDefault(j, false) && (verticesDistance[j]!! < verticesDistance[i]!! || nearestId == null)) {
                    nearestId = j
                }
            }
            if (nearestId == null || verticesDistance[nearestId] == Int.MAX_VALUE) break
            verticlesVisited[nearestId] = true

            val edgesFromNearest = graph.edges.filter { x -> x.value.verticesNumbers.first == nearestId || x.value.verticesNumbers.second == nearestId }.values

            for (edge in edgesFromNearest) {
                val to = if (nearestId == edge.verticesNumbers.first) edge.verticesNumbers.second else edge.verticesNumbers.first
                if (verticesDistance[nearestId]!! + edge.weight < verticesDistance[to]!!) {
                    verticesDistance[to] = verticesDistance[nearestId]!! + edge.weight
                    verticlesFrom[to] = nearestId
                }
            }
        }

        val resultPath = mutableListOf<Vertex<V>>()
        var finish: Int? = endVertexId
        while (finish != startVertexId) {
            if (finish == null) {
                resultPath.clear()
                break
            }
            resultPath.add(graph.vertices[finish]!!)
            finish = verticlesFrom[finish]
        }
        resultPath.add(graph.vertices[startVertexId]!!)
        resultPath.reverse()
        return PathResult(graph, resultPath)
    }
}