# Documentation

You need to clone our repository and run the app using

```
./gradlew run
```

## How to use

Firstly, you need to choose a graph

![graph-choose](src/main/resources/graph-choose.png)

## Undirected unweighted graph

Here is the example of our graph window

![graph](src/main/resources/graph.png)

If you click "Reset", graph will become as it was before you started manipulating it.

If you click "Algorithms", you will be suggested to use algorithms of this graph

![graph-algos](src/main/resources/graph-algos.png)

### Bridges

Bridge edges are highlighted with red color.

![bridge](src/main/resources/bridge.png)

### Cycles search for given vertex

You are suggested to put in a number of chosen vertex

![cycles-vertex-choose.png](src/main/resources/cycle-vertex-choose.png)

Then you receive highlighted cycle of this vertex, or vertex becomes highlighted with blue color if it doesn't belong to any cycle

![cycle](src/main/resources/cycle.png)

### Key vertices selection

You receive highlighted key vertices of this graph

![key-vertices](src/main/resources/key-vertices.png)

## Directed unweighted graph

Directed graph has some undirected graph algorithms, but also there is selection of strongly connected components.

### Strongly connected components selection

Each component is highlighted with its unique color.

![scc](src/main/resources/scc.png)

## Undirected weighted graph

In weighted graph, you can see weights of edges

![edges-weights](src/main/resources/edges-weights.png)

### Minimal spanning tree construction

Included edges are highlighted with red

![mst](src/main/resources/mst.png)

### Path finding

To find a path, you need to pick source and destination vertices

![path-vertices](src/main/resources/path-vertices.png)

Path is highlighted. Notice that we don't support negative weights for path finding in undirected graph.

![path](src/main/resources/path.png)

## Weighted directed graph
All its algorithms are already explained above