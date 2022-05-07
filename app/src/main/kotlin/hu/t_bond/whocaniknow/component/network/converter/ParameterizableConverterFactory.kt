package hu.t_bond.whocaniknow.component.network.converter

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ParameterizableConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Enum<*>, String>? =
        if (type is Class<*> && type.isEnum && type.isInstance(Parameterizable::class.java)) {
            Converter { enum ->
                try {
                    (enum as Parameterizable).toParameter()
                } catch (exception: Exception) {
                    null
                } ?: enum.toString()
            }
        } else {
            null
        }
}