package dataTests.stubs

import model.data.IVertexDataSerializer
import java.lang.reflect.Type

class IntVertexDataSerializer : IVertexDataSerializer<Int> {
    override fun serialize(data: Int): String = data.toString()

    override fun getSerializableType(): Type = Int::class.java

    override fun deserialize(data: String): Int = data.toInt()
}