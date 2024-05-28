package viewModel.graphViewModel.graphsViewModel

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.graphs.edges.WeightedEdge
import model.graphs.graphs.WeightedGraph
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.edgesViewModel.WeightedEdgeViewModel

class WeightedGraphViewModel<V>(
    override val graph: WeightedGraph<V>,
    showVerticesLabels: State<Boolean>,
    showEdgesLabels: State<Boolean>
) : AbstractGraphViewModel<V, WeightedEdge>(graph) {

    val verticesMap = graph.vertices.values.associateWith { v ->
        VertexViewModel(0.dp, 0.dp, Color.Gray, v, showVerticesLabels)
    }

    val edgesMap = graph.edges.values.associateWith { e ->
        val fst = verticesMap[graph.vertices[e.verticesNumbers.first]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        val snd = verticesMap[graph.vertices[e.verticesNumbers.second]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        WeightedEdgeViewModel(fst, snd, Color.Black, e, showEdgesLabels)
    }

    val vertices: Collection<VertexViewModel<V>>
        get() = verticesMap.values

    val edges: Collection<WeightedEdgeViewModel<V>>
        get() = edgesMap.values
}