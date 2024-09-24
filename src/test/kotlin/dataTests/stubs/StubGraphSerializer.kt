package dataTests.stubs

import model.data.AbstractGraphSerializer
import model.data.GraphRepresentation

class StubGraphSerializer : AbstractGraphSerializer() {

    var lastGraphRepresentation: GraphRepresentation? = null

    override fun deserializeGraph(name: String): GraphRepresentation {
        throw NotImplementedError()
    }

    override fun serializeGraphRepresentation(graph: GraphRepresentation) {
        lastGraphRepresentation = graph
    }

    override fun getNameForNextGraph(): String {
        throw NotImplementedError()
    }

    override fun isGraphAlreadyStored(name: String): Boolean = false
}