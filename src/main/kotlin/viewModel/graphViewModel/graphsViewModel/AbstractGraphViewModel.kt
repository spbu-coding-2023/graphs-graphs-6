package viewModel.graphViewModel.graphsViewModel

import model.graphs.edges.Edge
import model.graphs.graphs.AbstractGraph

abstract class AbstractGraphViewModel<V, E : Edge>(
    protected open val graph: AbstractGraph<V, E>
)