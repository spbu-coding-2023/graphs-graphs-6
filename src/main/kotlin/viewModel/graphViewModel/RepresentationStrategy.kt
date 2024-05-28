package viewModel.graphViewModel

import androidx.compose.ui.graphics.Color

interface RepresentationStrategy {
    fun <V> place(width: Double, height: Double, vertices: Collection<VertexViewModel<V>>)
    fun <V> highlight(vertices: Collection<VertexViewModel<V>>, color: Color)
}