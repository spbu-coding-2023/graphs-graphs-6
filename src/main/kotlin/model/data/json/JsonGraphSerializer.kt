package model.data.json

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.data.AbstractGraphSerializer
import model.data.GraphRepresentation
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

class JsonGraphSerializer : AbstractGraphSerializer() {

    private val storagePath = "./jsonGraphsStorage"

    init {
        if (!Files.isDirectory(Paths.get(storagePath))) Files.createDirectory(Paths.get(storagePath))
    }
    override fun deserializeGraph(name: String): GraphRepresentation {
        val text = File("$storagePath/$name.json").readText()
        return Json.decodeFromString<GraphRepresentation>(text)
    }

    override fun serializeGraphRepresentation(graph: GraphRepresentation) {
        val text = Json.encodeToString(graph)
        File(storagePath + "/" + graph.name + ".json").writeText(text)
    }

    override fun getNameForNextGraph(): String {
        var name = LocalDate.now().toString()
        while (isGraphAlreadyStored(name)) {
            name += ",new"
        }
        return name
    }

    override fun isGraphAlreadyStored(name: String): Boolean = Files.exists(Path.of("$storagePath/$name.json"))
}