package viewModel.graphViewModel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.algorithms.placement.YifanHuPlacement
import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

class YifanHuPlacementStrategy : RepresentationStrategy {
    override fun <V, E : Edge> place(
        width: Double,
        height: Double,
        graph: AbstractGraph<V, E>,
        vertices: Collection<VertexViewModel<V>>
    ) {
        val placemnt = YifanHuPlacement(graph).getPlacement(width.toInt(), height.toInt())

        for (vertex in vertices) {
            vertex.x = placemnt[vertex.v.value]?.first?.dp ?: 0f.dp
            vertex.y = placemnt[vertex.v.value]?.second?.dp ?: 0f.dp
        }
    }

    override fun <V> highlightVertices(vertices: Collection<VertexViewModel<V>>, color: Color) {
        vertices
            .onEach {
                it.color = color
            }
    }

    override fun <V> highlightEdges(edges: Collection<EdgeViewModel<V>>, color: Color) {
        edges
            .onEach {
                it.color = color
            }
    }
}