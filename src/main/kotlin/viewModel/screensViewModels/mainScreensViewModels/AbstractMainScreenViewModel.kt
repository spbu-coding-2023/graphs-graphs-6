package viewModel.screensViewModels.mainScreensViewModels

import androidx.compose.runtime.mutableStateOf
import model.graphs.edges.Edge
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.graphsViewModel.AbstractGraphViewModel

abstract class AbstractMainScreenViewModel<V, E : Edge>(
    val representationStrategy: RepresentationStrategy
) {
    val showVerticesLabels = mutableStateOf(false)
    val showEdgesLabels = mutableStateOf(false)
    abstract val graphViewModel: AbstractGraphViewModel<V, E>
}