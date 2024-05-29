package dataTests.stubs

import model.data.AbstractGraphSerializer
import model.data.GraphRepresentation

class StubGraphDeserializer : AbstractGraphSerializer() {
    override fun deserializeGraph(name: String): GraphRepresentation {
        if (name == "weighted") return GraphRepresentation(
            "weighted",
            "int",
            listOf("0", "1", "2", "3", "4"),
            listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3), Pair(1, 4), Pair(2, 4), Pair(3, 2), Pair(3, 4)),
            false,
            true,
            listOf(3, 6, 1, 8, 4, 4, 12)
        )
        if (name == "ordinary") return GraphRepresentation(
            "ordinary",
            "int",
            listOf("0", "1", "2", "3", "4"),
            listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3), Pair(1, 4), Pair(2, 4), Pair(3, 2), Pair(3, 4)),
            false,
            false,
            listOf()
        )
        if (name == "directed") return GraphRepresentation(
            "directed",
            "int",
            listOf("5", "6"),
            listOf(Pair(0, 1)),
            true,
            false,
            listOf()
        )
        if (name == "weighteddirected") return GraphRepresentation(
            "weighteddirected",
            "int",
            listOf("5", "6"),
            listOf(Pair(0, 1)),
            true,
            true,
            listOf(156)
        )
        throw Error("Try deserialize not containable graph")
    }

    override fun serializeGraphRepresentation(graph: GraphRepresentation) {
        throw NotImplementedError()
    }

    override fun getNameForNextGraph(): String {
        throw NotImplementedError()
    }

    override fun isGraphAlreadyStored(name: String): Boolean = name == "weighteddirected" || name == "weighted" || name == "directed" || name == "ordinary"
}