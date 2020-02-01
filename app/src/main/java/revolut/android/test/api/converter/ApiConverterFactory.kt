package revolut.android.test.api.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ApiConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, Any> {
        val delegate: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(
            this,
            type,
            annotations
        )
        return ApiConverter(delegate)
    }
}