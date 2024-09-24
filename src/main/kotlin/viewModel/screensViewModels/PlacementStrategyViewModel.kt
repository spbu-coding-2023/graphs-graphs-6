package viewModel.screensViewModels

import viewModel.graphViewModel.CircularPlacementStrategy
import viewModel.graphViewModel.RepresentationStrategy
import viewModel.graphViewModel.YifanHuPlacementStrategy

class PlacementStrategyViewModel {
    internal var placementStrategy: RepresentationStrategy = CircularPlacementStrategy()

    fun selectYifanHu() {
        placementStrategy = YifanHuPlacementStrategy()
    }
    fun selectCircular() {
        placementStrategy = CircularPlacementStrategy()
    }
}