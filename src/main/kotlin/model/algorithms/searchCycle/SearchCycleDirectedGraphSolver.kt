package model.algorithms.searchCycle

import model.graphs.graphs.DirectedGraph

class SearchCycleDirectedGraphSolver<V>(graph: DirectedGraph<V>) : SearchCycleGraphSolver<V>(graph) {
    override fun parentsCheck(currVertex: Int, parentVertex: Int): Boolean {
        return false
    }
}