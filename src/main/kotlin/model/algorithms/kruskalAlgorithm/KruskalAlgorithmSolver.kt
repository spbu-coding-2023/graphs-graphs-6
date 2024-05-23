package model.algorithms.kruskalAlgorithm

import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph

class KruskalAlgorithmSolver<V>(graph: WeightedGraph<V>) { // non-directed weighted graphs
    private val numOfVertices = graph.lastVertexNumber
    private val sortedEdges = sortEdges(graph)

    private val disjointSet = IntArray(numOfVertices) { -1 }
    private val mst = mutableListOf<WeightedEdge>()

    fun doKruskalAlgorithm(): List<WeightedEdge> {
        for (edge in sortedEdges) {
            if (mst.size >= numOfVertices - 1) {
                break
            }

            if (find(disjointSet, edge.verticesNumbers.first) != find(disjointSet, edge.verticesNumbers.second)) {
                union(disjointSet, edge.verticesNumbers.first, edge.verticesNumbers.second)
                mst.add(edge)
            }
        }

        return mst
    }

    private fun sortEdges(graph: WeightedGraph<V>): List<WeightedEdge> {
        return graph.edges.values.sortedBy { it.weight }
    }

    private fun find(parents: IntArray, i: Int): Int {
        if (parents[i] < 0) {
            return i
        }

        return find(parents, parents[i]).also { parents[i] = it }
    }

    private fun union(parents: IntArray, i: Int, j: Int) {
        val root1 = find(parents, i)
        val root2 = find(parents, j)

        if (root1 != root2) {
            if (parents[root1] < parents[root2]) {
                parents[root1] += parents[root2]
                parents[root2] = root1
            } else {
                parents[root2] += parents[root1]
                parents[root1] = root2
            }
        }
    }
}
