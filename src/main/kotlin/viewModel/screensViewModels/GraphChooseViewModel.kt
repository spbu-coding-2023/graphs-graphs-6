package viewModel.screensViewModels

class GraphChooseViewModel<V> {
    internal var isDirected = false
    internal var isWeighted = false

    fun selectGraph() {
        isDirected = false
        isWeighted = false
    }

    fun selectDirectedGraph() {
        isDirected = true
        isWeighted = false
    }

    fun selectWeightedGraph() {
        isDirected = false
        isWeighted = true
    }

    fun selectWeightedDirectedGraph() {
        isDirected = true
        isWeighted = true
    }
}