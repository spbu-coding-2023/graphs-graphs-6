# Graph analysis app
This app provides the ability to store graphs and analyze them.
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
Using our app, you can fully manipulate the graph: add new vertices and delete existent, connect nodes with the edges and delete these edges too.
In our GUI, graph is drawn with usage of graph plane layout algorithm.
You can store graphs in 3 formats:
* .json file.
* SQLite database.
* Neo4j database.

We provide the opportunity to solve these problems with our app:
* Selecting key vertices of the graph.
* Searching for communities in the graph.
* Selecting strongly connected components (directed graph).
* Searching for bridges (undirected graph).
* Search for cycles for a given vertex.
* Construction of a minimal spanning tree (weighted undirected graph).
* Finding the shortest path between a pair of vertices (weighted graph). We also support negative weights.
## File structure <a name="filestructure"></a>
TODO
## How to use <a name="howtouse"></a>
See our wiki page. (TODO)
## Technologies used in the library <a name="technologies"></a>
* [Kotlin 1.9.23](https://kotlinlang.org)
* [Gradle 8.5](https://gradle.org)
* [JDK 17](https://openjdk.org)
* [JGraphT 1.5.2](https://jgrapht.org/)
## Our team and responsibility areas <a name="team"></a>
* Vyacheslav Kochergin - .json graph storage, key vertices searching, strongly connected components selection, finding the shortest path with negative weights, documentation. [GitHub](https://github.com/VyacheslavIurevich), [Contact](https://t.me/se4life).
* Vadim Marchenko - Neo4j graph storage, graph plane layout, searching for bridges, finding the shortest path without negative weights, CI. [GitHub](https://github.com/elbananium), [Contact](https://t.me/elbananum).
* Dmitri Kuznetsov - SQLite graph storage, searching for communities in the graph, searching for cycles, minimal spanning tree construction, GUI manager. [GitHub](https://github.com/f1i3g3), [Contact](https://t.me/f1i3g3).
## Project status <a name="status"></a>
In development.
## Contributing <a name="contributing"></a>
See [CONTRIBUTING.md](./CONTRIBUTING.md)
## License <a name="license"></a>
See [LICENSE.md](./LICENSE.md)