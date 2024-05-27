package viewModel.graphViewModel.edgesViewModel

import androidx.compose.runtime.State
import model.graphs.edges.WeightedEdge
import viewModel.graphViewModel.VertexViewModel

class WeightedEdgeViewModel<V>(
    u: VertexViewModel<V>,
    v: VertexViewModel<V>,
    private val e: WeightedEdge,
    private val _labelVisible: State<Boolean>
) : EdgeViewModel<V>(u, v) {
    override val label
        get() = e.weight.toString()
    override val labelVisible
        get() = _labelVisible.value
}