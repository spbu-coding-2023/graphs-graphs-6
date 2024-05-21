package algorithms.bridgeFinder

import graphs.graphs.Graph
import java.util.Stack
import kotlin.math.min

open class BridgeFinder<V>(protected val graph: Graph<V>) {

    private var verticesCount = 0
    private var timer = 0
    private lateinit var visited: BooleanArray
    private lateinit var bridges: MutableList<Pair<Int, Int>>
    private lateinit var verticlsStack: Stack<Int>
    private lateinit var enterTimeInVertex: Array<Int>
    private lateinit var enterTimeInComponent: Array<Int>
    private lateinit var adjencyMap: Map<Int, MutableSet<Int>>
    private fun updateInfo() {
        timer = 0
        verticesCount = graph.vertices.size
        visited = BooleanArray(verticesCount)
        bridges = mutableListOf<Pair<Int, Int>>()
        verticlsStack = Stack<Int>()
        enterTimeInVertex = Array<Int>(verticesCount) { -1 }
        enterTimeInComponent = Array<Int>(verticesCount) { -1 }
        adjencyMap = graph.toAdjacencyMap()
    }

    open fun findBridges(): List<Pair<Int, Int>> {
        updateInfo()
        for (vertexNumber in 0..<verticesCount) {
            if (!visited[vertexNumber]) dfs(vertexNumber)
        }

        return bridges.toList()
    }

    private fun dfs(startVertex: Int, startParent: Int = -1) {
        var parent = startParent
        var vertex = startVertex
        verticlsStack.push(vertex)
        visited[vertex] = true
        enterTimeInVertex[vertex] = timer
        enterTimeInComponent[vertex] = timer++

        while (!verticlsStack.isEmpty()) {
            val adjencyVertexSet = adjencyMap[vertex] ?: throw NullPointerException()

            for (destinationVertex in adjencyVertexSet) {
                if (parent == destinationVertex) continue
                if (visited[destinationVertex]) {
                    enterTimeInComponent[vertex] =
                        min(enterTimeInComponent[vertex], enterTimeInVertex[destinationVertex])
                } else {
                    verticlsStack.push(destinationVertex)
                    parent = vertex
                    vertex = destinationVertex
                    break
                }
            }

            if (visited[vertex]) {
                if (verticlsStack.size == 1) {
                    verticlsStack.pop()
                } else {
                    val to = verticlsStack.pop()
                    val from = verticlsStack.pop()
                    enterTimeInComponent[from] = min(enterTimeInComponent[from], enterTimeInComponent[to])
                    if (enterTimeInComponent[to] > enterTimeInVertex[from]) bridges.add(Pair(from, to))
                    vertex = from
                    parent = when (verticlsStack.isEmpty()) {
                        true -> -1
                        else -> verticlsStack.peek()
                    }
                    verticlsStack.push(vertex)
                }
            } else {
                visited[vertex] = true
                enterTimeInVertex[vertex] = timer
                enterTimeInComponent[vertex] = timer++
            }
        }
    }
}