package view.graphView.graphsView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.graphs.graphs.DirectedGraph
import view.graphView.edgesView.directedEdgeView
import view.graphView.edgesView.edgeView
import view.graphView.vertexView
import viewModel.graphViewModel.graphsViewModel.GraphViewModel

@Composable
fun <V> graphView(
    viewModel: GraphViewModel<V>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        viewModel.vertices.forEach { v ->
            vertexView(v, Modifier)
        }
        viewModel.edges.forEach { e ->
            if (viewModel.graph is DirectedGraph<V>) directedEdgeView(e, Modifier) else edgeView(e, Modifier)
        }
    }
}
