package viewModel.graphViewModel.edgesViewModel

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import model.graphs.edges.WeightedEdge
import viewModel.graphViewModel.VertexViewModel

class WeightedEdgeViewModel<V>(
    u: VertexViewModel<V>,
    v: VertexViewModel<V>,
    color: Color,
    private val e: WeightedEdge,
    private val _labelVisible: State<Boolean>
) : EdgeViewModel<V>(u, v, color) {
    override val label
        get() = e.weight.toString()
    override val labelVisible
        get() = _labelVisible.value
}