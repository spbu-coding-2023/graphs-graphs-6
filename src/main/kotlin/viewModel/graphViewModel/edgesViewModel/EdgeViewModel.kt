package viewModel.graphViewModel.edgesViewModel

import model.graphs.edges.Edge
import viewModel.graphViewModel.VertexViewModel

open class EdgeViewModel<V>(
    val u: VertexViewModel<V>,
    val v: VertexViewModel<V>,
    private val e: Edge
) {
    open val label: String = ""
    open val labelVisible: Boolean = false
}