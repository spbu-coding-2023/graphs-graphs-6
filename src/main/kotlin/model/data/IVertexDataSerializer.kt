package model.data

import java.lang.reflect.Type

interface IVertexDataSerializer<V> {
    fun serialize(data: V): String

    fun getSerializableType(): Type

    fun deserialize(data: String): V
}