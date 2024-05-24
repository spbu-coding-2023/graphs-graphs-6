package viewModel.graphViewModel

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.graphs.graphs.Graph
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

class GraphViewModel<V>(
    private val graph: Graph<V>,
    showVerticesLabels: State<Boolean>,
    showEdgesLabels: State<Boolean>
) {
    private val _vertices = graph.vertices.values.associateWith { v ->
        VertexViewModel(0.dp, 0.dp, Color.Gray, v, showVerticesLabels)
    }
    private val _edges = graph.edges.values.associateWith { e ->
        val fst = _vertices[graph.vertices[e.verticesNumbers.first]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        val snd = _vertices[graph.vertices[e.verticesNumbers.second]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        EdgeViewModel(fst, snd, e)
    }

    val vertices: Collection<VertexViewModel<V>>
        get() = _vertices.values

    val edges: Collection<EdgeViewModel<V>>
        get() = _edges.values
}