package hu.t_bond.whocaniknow.component.network.converter

import com.google.gson.*
import java.lang.reflect.Type

class ParameterizableTypeHierarchyAdapter<T>(private val clazz: Class<T>) : JsonDeserializer<T>,
    JsonSerializer<T> where T : Enum<T>, T : Parameterizable {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): T {
        val value = json.asString

        return clazz.enumConstants?.firstOrNull {
            it.toParameter().equals(value, true)
        } ?: throw JsonParseException("The requested enum value was not found")
    }

    override fun serialize(
        src: T,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.toParameter())
    }
}