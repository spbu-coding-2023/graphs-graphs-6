package model.algorithms.placement

import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph
import org.gephi.graph.api.GraphModel
import org.gephi.graph.api.Node
import org.gephi.layout.plugin.force.StepDisplacement
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout
import kotlin.random.Random

open class YifanHuPlacement<V, E : Edge>(val graph: AbstractGraph<V, E>) {
    fun getPlacement(width: Int, height: Int): Map<V, Pair<Float, Float>> {
        val random = Random(1L)

        val graphModel = GraphModel.Factory.newInstance()
        val graphType = graphModel.undirectedGraph

        val nodes = mutableMapOf<V, Node>()
        for (vertex in graph.vertices.values) {
            val node: Node = graphModel.factory().newNode(vertex.value.toString())
            node.setX(random.nextFloat() * width)
            node.setY(random.nextFloat() * height)
            nodes[vertex.value] = node

            graphType.addNode(node)
        }

        for (edge in graph.edges.values) {
            val firstVertex = graph.getVertexValue(edge.verticesNumbers.first)
            val secondVertex = graph.getVertexValue(edge.verticesNumbers.second)
            val e: org.gephi.graph.api.Edge = graphModel.factory().newEdge(
                nodes[firstVertex],
                nodes[secondVertex],
                1,
                false
            )
            graphType.addEdge(e)
        }
        val layout = YifanHuLayout(null, StepDisplacement(1f))
        layout.setGraphModel(graphModel)
        layout.initAlgo()
        layout.resetPropertiesValues()
        layout.optimalDistance = 100f
        layout.relativeStrength = 0.2f
        layout.initialStep = 20.0f
        layout.stepRatio = 0.95f
        layout.isAdaptiveCooling = true

        var i = 0
        while (i < 1000 && layout.canAlgo()) {
            layout.goAlgo()
            i++
        }
        layout.endAlgo()
        val placement = mutableMapOf<V, Pair<Float, Float>>()
        for (vertex in graph.vertices.values) {
            val n = graphType.getNode(vertex.value.toString())
            val x = width * n.x()
            val y = height * n.y()
            placement[vertex.value] = Pair(x, y)
        }

        return placement
    }
}