package hu.t_bond.whocaniknow.component.network.converter

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class QueryParameterArrayTransformatorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var url = request.url()

        for (parameterName in url.queryParameterNames()) {
            val queryParameterValues = url.queryParameterValues(parameterName)
            if (queryParameterValues.size < 1) {
                continue
            }

            val formattedValues = queryParameterValues.joinToString(",")
            url = url.newBuilder()
                .removeAllQueryParameters(parameterName)
                .addEncodedQueryParameter(parameterName, formattedValues)
                .build()

            request = request.newBuilder().url(url).build()

        }

        return chain.proceed(request)
    }
}