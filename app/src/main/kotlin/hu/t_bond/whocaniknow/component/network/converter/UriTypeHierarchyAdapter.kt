package hu.t_bond.whocaniknow.component.network.converter

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

/**
 * Serialize/Deserialize Android's [Uri] class.
 * You can register this by
 * ``registerTypeHierarchyAdapter(Uri.class, new UriTypeHierarchyAdapter())`'.
 */
// https://gist.github.com/ypresto/3607f395ac4ef2921a8de74e9a243629
class UriTypeHierarchyAdapter : JsonDeserializer<Uri>, JsonSerializer<Uri> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Uri {
        return Uri.parse(json.asString)
    }

    override fun serialize(
        src: Uri,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        // Note that Uri is abstract class.
        return JsonPrimitive(src.toString())
    }
}