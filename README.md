# Graphs visualizer app

This app is a graph visualizer with ability to analyze the graph.

## Table of contents

* [Functionality](#functionality)
* [File structure](#filestructure)
* [How to use](#howtouse)
* [Technologies used in the library](#technologies)
* [Our team and responsibility areas](#team)
* [Project status](#status)
* [Contributing](#contributing)
* [License](#license)

## Functionality <a name="functionality"></a>

We provide the opportunity to solve these problems with our app:

* Selecting key vertices of the graph.
* Selecting strongly connected components (directed graph).
* Searching for bridges (undirected graph).
* Search for cycles for a given vertex.
* Construction of a minimal spanning tree (weighted undirected graph).
* Finding the shortest path between a pair of vertices (weighted graph). We also support negative weights (only directed
  graph).

## File structure <a name="filestructure"></a>

```
src
├── main/kotlin
│  │
│  ├── model
│  │  ├── algorithms
│  │  │   ├── bridgeFinder
│  │  │   │   ├── BridgeFinder.kt // bridge finding algorithm implementation
│  │  │   │
│  │  │   ├── keyVerticesSelection
│  │  │   │   ├── KeyVerticesSelectionSolver.kt // key vertices selection algorithm implementation
│  │  │   │
│  │  │   ├── kruskalAlgorithm
│  │  │   │   ├── KruskalALgorithmSolver.kt // minimal spanning tree building algorithm implementation
│  │  │   │
│  │  │   ├── pathSearch
│  │  │   │   ├── djikstra
│  │  │   │   │   ├── DjikstraAlgorithm.kt // finding path between two vertices without negative weights for undirected graph algorithm implementation
│  │  │   │   │
│  │  │   │   ├── fordBellman
│  │  │   │   │   ├── FordBellmanAlgorithm.kt // finding path between two vertices with negative weights for directed graph algorithm implementation
│  │  │   │   │
│  │  │   │   ├── PathResult.kt // result of finding path class, same for both algorithms
│  │  │   │
│  │  │   ├── searchCycle
│  │  │   │   ├── SearchCycleForVertexInDirectedGraphSolver.kt // searching cycle for given vertex in directed graph algorithm implementation
│  │  │   │   ├── SearchCycleForVertexInGraphSolver.kt // searching cycle for given vertex algorithm implementation
│  │  │   │
│  │  │   ├── stronglyConnectedComponentsSelection
│  │  │   │   ├── StronglyConnectedComponentsSelectionSolver.kt // strongly connected components selection algorithm implementation
│  │  │
│  │  ├── graphs 
│  │  │   ├── edges
│  │  │   │   ├── Edge.kt // unweighted edge class
│  │  │   │   ├── WeightedEdge.kt // weighted edge class
│  │  │   │
│  │  │   ├── graphs
│  │  │   │   ├── AbstractGraph.kt // abstract graph class
│  │  │   │   ├── DirectedGraph.kt // directed graph class
│  │  │   │   ├── Graph.kt // graph class
│  │  │   │   ├── WeightedDirectedGraph.kt // weighted directed graph class
│  │  │   │   ├── WeightedGraph.kt // weighted graph class
│  │  │   │
│  │  │   ├── vertex
│  │  │   │   ├── Vertex.kt // vertex class
│  │
│  ├── view
│  │  ├── graphView
│  │  │   ├── edgesView
│  │  │   │  ├── DirectedEdgeView.kt // directed edge view
│  │  │   │  ├── EdgeView.kt // edge view
│  │  │   │
│  │  │   ├── graphsView
│  │  │   │  ├── GraphView.kt // graph view
│  │  │   │  ├── WeightedGraphView.kt // weighted graph view
│  │  │   │
│  │  │   ├── VertexView.kt // vertex view
│  │  │ 
│  │  ├── screens
│  │  │   ├── mainScreens
│  │  │   │  ├── MainScreen.kt // main screen
│  │  │   │  ├── MainScreenWeightedGraph.kt // main screen with weighted graph
│  │  │   │ 
│  │  │   ├── GraphChooseScreen.kt // graph type choose screen
│  │
│  ├── viewModel
│  │  ├── graphViewModel
│  │  │   ├── edgesViewModel
│  │  │   │  ├── EdgeViewModel.kt // edge viewmodel class
│  │  │   │  ├── WeightedEdgeViewModel.kt // edge viewmodel class
│  │  │   │
│  │  │   ├── graphsViewModel
│  │  │   │  ├── GraphViewModel.kt //  graph viewmodel class
│  │  │   │  ├── WeightedGraphViewModel.kt //  weighted graph viewmodel class
│  │  │   │
│  │  │   ├── CircularPlacementStrategy.kt // circular placement strategy class
│  │  │   ├── RepresentationStrategy.kt // representation strategy interface
│  │  │   ├── VertexViewModel.kt // vertex viewmodel class
│  │  │
│  │  ├── screensViewModels
│  │  │   ├── mainScreensViewModels
│  │  │   │  ├── MainScreenViewModel.kt // main screen viewmodel class
│  │  │   │  ├── MainScreenViewModelWeightedGraph // main screen with weighted graph viewmodel class
│  │  │   │
│  │  │   ├── GraphChooseViewModel.kt // graph choose window viewmodel class
│  │
│  ├── Main.kt // entry point, main app file
│
├── test/kotlin
│  ├── algorithmsTests
│  │  ├── BridgeFinderTests.kt // bridge finding tests
│  │  ├── DjikstraTests.kt.kt // Dijkstra algorithm tests
│  │  ├── FordBellmanTests.kt // Ford-Bellman algorithm tests
│  │  ├── KeyVerticesSelectionSolverDirectedGraphTests.kt // key vertices selection in directed graph
│  │  ├── KeyVerticesSelectionSolverTests.kt // key vertices selection tests
│  │  ├── KruskalAlgorithmSolverTests.kt // MST building tests
│  │  ├── SearchCycleForVertexInDirectedGraphSolverTests.kt // cycle search in directed graph tests
│  │  ├── SearchCycleForVertexInGraphSolverTests.kt // cycle search in graph tests
│  │  ├── StronglyConnectedComponentsSelectionSolverTests.kt // strongly connected components selection tests
│  │
│  ├── graphsTests
│  │  ├── AbstractGraphTests.kt // abstract graph tests
│  │  ├── AbstractWeightedGraphTests.kt // abstract weighted graph tests
│  │  ├── DirectedGraphTests.kt // directed graph tests
│  │  ├── GraphTests.kt // graph tests
│  │  ├── WeightedDirectedGraphTests.kt // weighted directed graph tests
│  │  ├── WeightedGraphTests.kt // weighted graph tests
│  │
```

## How to use <a name="howtouse"></a>

See [DOCS.md](./DOCS.md)

## Technologies used in the library <a name="technologies"></a>

* [Kotlin 1.9.23](https://kotlinlang.org)
* [Gradle 8.5](https://gradle.org)
* [JDK 17](https://openjdk.org)
* [Jetpack Compose](https://developer.android.com/develop/ui/compose)
* [ktlint](https://github.com/pinterest/ktlint)

## Our team and responsibility areas <a name="team"></a>

* Vyacheslav Kochergin - key vertices selection, strongly connected components selection, finding the shortest path with
  negative weights, GUI,
  documentation. [GitHub](https://github.com/VyacheslavIurevich), [Contact](https://t.me/se4life).
* Vadim Marchenko - .json graph storage, searching for bridges, finding the shortest path without negative weights,
  CI. [GitHub](https://github.com/elbananium), [Contact](https://t.me/elbananum).
* Dmitri Kuznetsov - searching cycles for given vertex, minimal spanning tree
  construction. [GitHub](https://github.com/f1i3g3), [Contact](https://t.me/f1i3g3).

## Project status <a name="status"></a>

Ready to be checked (as much as we managed to do in time).

## Contributing <a name="contributing"></a>

See [CONTRIBUTING.md](./CONTRIBUTING.md)

## License <a name="license"></a>

See [LICENSE.md](./LICENSE.md)

## Acknowledgements

Huge thanks to [@IliaSuponeff](https://github.com/IliaSuponeff)
from [Team 12](https://github.com/spbu-coding-2023/graphs-graphs-12/) for allowing us to use his scripts for CI (
distributed under the MIT license). Also we have our GUI based on developments
from [gui-workshop](https://github.com/spbu-coding-2023/gui-workshop) repository (distributed under the MIT license).