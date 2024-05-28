package model.algorithms.kruskalAlgorithm

import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph

class KruskalAlgorithmSolver<V>(graph: WeightedGraph<V>) { // non-directed weighted connected graphs
    private var edges = graph.edges.values
    private val numOfVertices = graph.lastVertexNumber

    private val disjointSet: MutableList<Int> = mutableListOf()
    private val minimalSpanningTree = mutableListOf<WeightedEdge>()

    fun doKruskalAlgorithm(): Pair<Boolean, List<WeightedEdge>> {
        if (numOfVertices <= 1) { // empty graph or graph with one vertex
            return Pair(false, minimalSpanningTree.toList())
        }

        initializeDisjointSet()
        val sortedEdges = edges.sortedBy { it.weight }

        for (edge in sortedEdges) {
            if (minimalSpanningTree.size == numOfVertices - 1) {
                break
            }

            if (findMST(disjointSet, edge.verticesNumbers.first - 1) !=
                findMST(disjointSet, edge.verticesNumbers.second - 1)
            ) {
                unionMST(disjointSet, edge.verticesNumbers.first - 1, edge.verticesNumbers.second - 1)
                minimalSpanningTree.add(edge)
            }
        }

        if (minimalSpanningTree.size < numOfVertices - 1) {
            return Pair(false, emptyList())
        }

        return Pair(true, minimalSpanningTree.toList())
    }

    private fun initializeDisjointSet() {
        for (i in 0..<numOfVertices) {
            disjointSet.add(-1)
        }
    }

    private fun findMST(parents: MutableList<Int>, i: Int): Int {
        if (parents[i] < 0) {
            return i
        }

        return findMST(parents, parents[i]).also { parents[i] = it }
    }

    private fun unionMST(parents: MutableList<Int>, i: Int, j: Int) {
        val root1 = findMST(parents, i)
        val root2 = findMST(parents, j)

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
