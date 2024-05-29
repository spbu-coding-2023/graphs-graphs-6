package viewModel.graphViewModel.graphsViewModel

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.graphs.graphs.Graph
import viewModel.graphViewModel.VertexViewModel
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

class GraphViewModel<V>(
    val graph: Graph<V>,
    showVerticesLabels: State<Boolean>
) {
    val verticesMap = graph.vertices.values.associateWith { v ->
        VertexViewModel(0.dp, 0.dp, Color.Gray, v, showVerticesLabels)
    }

    val edgesMap = graph.edges.values.associateWith { e ->
        val fst = verticesMap[graph.vertices[e.verticesNumbers.first]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        val snd = verticesMap[graph.vertices[e.verticesNumbers.second]]
            ?: throw IllegalStateException("VertexView for ${graph.vertices[e.verticesNumbers.first]} not found")
        EdgeViewModel(fst, snd, Color.Black)
    }

    val vertices: Collection<VertexViewModel<V>>
        get() = verticesMap.values

    val edges: Collection<EdgeViewModel<V>>
        get() = edgesMap.values
}