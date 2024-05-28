package algorithms.searchCycle

import graphs.graphs.DirectedGraph

class SearchCycleForVertexInDirectedGraphSolver<V>(graph: DirectedGraph<V>) :
    SearchCycleForVertexInGraphSolver<V>(graph) {
    override fun parentsCheck(currVertex: Int, parentVertex: Int): Boolean {
        return false
    }
}