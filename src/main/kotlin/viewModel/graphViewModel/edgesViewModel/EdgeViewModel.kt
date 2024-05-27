package viewModel.graphViewModel.edgesViewModel

import viewModel.graphViewModel.VertexViewModel

open class EdgeViewModel<V>(
    val u: VertexViewModel<V>,
    val v: VertexViewModel<V>
) {
    open val label: String = ""
    open val labelVisible: Boolean = false
}