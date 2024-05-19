package algorithms.bridgeFinder

import graphs.graphs.Graph

open class BridgeFinder<V>(protected open val graph: Graph<V>) {
    public fun findBridges(): List<Pair<Int, Int>> {
        throw NotImplementedError()
    }
}