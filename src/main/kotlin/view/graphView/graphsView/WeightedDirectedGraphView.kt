package view.graphView.graphsView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import view.graphView.edgesView.directedEdgeView
import view.graphView.vertexView
import viewModel.graphViewModel.graphsViewModel.WeightedGraphViewModel

@Composable
fun <V> weightedDirectedGraphView(
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
            directedEdgeView(e, Modifier)
        }
    }
}
