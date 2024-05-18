package algorithms.keyVerticesSelection

import graphs.graphs.Graph
import java.util.Collections.max
import java.util.SortedMap
import kotlin.Double.Companion.MAX_VALUE
import kotlin.math.pow
import kotlin.math.sqrt

open class KeyVerticesSelectionSolver<V>(protected open val graph: Graph<V>) {
    private lateinit var graphMap: Map<Int, Set<Int>>
    private var alpha = 0.15
    private val threshold = 0.00001
    private val stochasticMatrix: MutableMap<Int, MutableMap<Int, Double>> = mutableMapOf()
    private val visited: MutableMap<Int, Boolean> = mutableMapOf()
    private val stationaryDistribution: MutableMap<Int, Double> = mutableMapOf()

    private fun dfsStochasticMatrix(vertexNum: Int) {
        if (visited[vertexNum] == true) return
        visited[vertexNum] = true
        val nextVertices = graphMap[vertexNum] ?: return
        for (nextVertexNum in nextVertices) {
            if (stochasticMatrix[vertexNum] == null) stochasticMatrix[vertexNum] = mutableMapOf()
            stochasticMatrix[vertexNum]?.set(
                nextVertexNum,
                (1 - alpha) * (1 / nextVertices.size) + alpha / graph.vertices.keys.size
            )
            dfsStochasticMatrix(nextVertexNum)
        }
    }

    private fun initializeStochasticMatrix() {
        for (vertexNum in graph.vertices.keys) dfsStochasticMatrix(vertexNum)
    }

    private fun initializeStartVector(): MutableMap<Int, Double> {
        val vector: MutableMap<Int, Double> = mutableMapOf()
        for (vertexNum in graph.vertices.keys) vector[vertexNum] = 1 / graph.vertices.keys.size.toDouble()
        return vector
    }

    private fun multiplyVectorByStochasticMatrix(vector: MutableMap<Int, Double>): MutableMap<Int, Double> {
        val product: MutableMap<Int, Double> = mutableMapOf()
        for (vertexNum in graph.vertices.keys) {
            for (vertexIt in graph.vertices.keys) {
                val matrixProbability = stochasticMatrix[vertexIt]?.get(vertexNum)
                val vectorProbability = vector[vertexIt]
                if (matrixProbability == null || vectorProbability == null) continue
                if (product[vertexNum] == null) product[vertexNum] = vectorProbability * matrixProbability
                else {
                    val currValue = product[vertexNum]
                    if (currValue != null) {
                        product[vertexNum] = currValue + vectorProbability * matrixProbability
                    }
                }
            }
        }
        return product
    }

    private fun countDistance(firstVector: MutableMap<Int, Double>, secondVector: MutableMap<Int, Double>): Double {
        var distance = 0.0
        for (vertexNum in graph.vertices.keys) {
            val firstValue = firstVector[vertexNum]
            val secondValue = secondVector[vertexNum]
            if (firstValue == null || secondValue == null) return 0.0
            distance += (firstValue - secondValue).pow(2)
        }
        return sqrt(distance)
    }

    private fun findStationaryDistribution(): SortedMap<Int, Double> {
        var distance = MAX_VALUE
        var vector = initializeStartVector()
        while (distance > threshold) {
            val nextVector = multiplyVectorByStochasticMatrix(vector)
            distance = countDistance(vector, nextVector)
            vector = nextVector
        }
        return vector.toSortedMap()
    }

    fun selectKeyVertices(): List<Int> {
        graphMap = graph.toAdjacencyMap()
        initializeStochasticMatrix()
        val stationaryDistribution = findStationaryDistribution()
        val keyVertices: MutableList<Int> = mutableListOf()
        for (vertexNum in graph.vertices.keys) {
            val pageRank = stationaryDistribution[vertexNum] ?: continue
            if (pageRank >= 0.5 * max(stationaryDistribution.values)) keyVertices.add(vertexNum)
        }
        return keyVertices
    }
}
