package viewModel.graphViewModel

import androidx.compose.ui.graphics.Color
import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

interface RepresentationStrategy {
    fun <V, E : Edge> place(width: Double, height: Double, graph : AbstractGraph<V, E> ,vertices: Collection<VertexViewModel<V>>)
    fun <V> highlightVertices(vertices: Collection<VertexViewModel<V>>, color: Color)
    fun <V> highlightEdges(edges: Collection<EdgeViewModel<V>>, color: Color)
}