package view.graphView.graphsView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.graphs.graphs.WeightedDirectedGraph
import view.graphView.edgesView.directedEdgeView
import view.graphView.edgesView.edgeView
import view.graphView.vertexView
import viewModel.graphViewModel.graphsViewModel.WeightedGraphViewModel

@Composable
fun <V> weightedGraphView(
    viewModel: WeightedGraphViewModel<V>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        viewModel.vertices.forEach { v ->
            vertexView(v, Modifier)
        }
        viewModel.edges.forEach { e ->
            if (viewModel.graph is WeightedDirectedGraph<V>) directedEdgeView(e, Modifier) else edgeView(e, Modifier)
        }
    }
}
