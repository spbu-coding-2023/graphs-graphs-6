package viewModel.graphViewModel

import androidx.compose.ui.graphics.Color
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

interface RepresentationStrategy {
    fun <V> place(width: Double, height: Double, vertices: Collection<VertexViewModel<V>>)
    fun <V> highlightVertices(vertices: Collection<VertexViewModel<V>>, color: Color)
    fun <V> highlightEdges(edges: Collection<EdgeViewModel<V>>, color: Color)
}