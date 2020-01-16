package com.marijannovak.autismhelper.data.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomConverterFactory(private val mFactoryMap: Map<Class<*>, Converter.Factory>) : Converter.Factory() {

    @Retention(AnnotationRetention.RUNTIME)
    annotation class Json

    @Retention(AnnotationRetention.RUNTIME)
    annotation class Xml

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        for (annotation: Annotation in annotations) {
            val factory = mFactoryMap[annotation.annotationClass.javaObjectType]
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }

        return mFactoryMap[Json::class.java]?.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<out Annotation>, methodAnnotations: Array<out Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return mFactoryMap[Json::class.java]?.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }

    internal class Builder {
        private var mFactoryMap: MutableMap<Class<*>, Converter.Factory> = LinkedHashMap()

        fun add(factoryType: Class<out Annotation>?, factory: Converter.Factory?): Builder {
            if (factoryType == null) throw NullPointerException("factoryType is null")
            if (factory == null) throw NullPointerException("factory is null")
            mFactoryMap[factoryType] = factory
            return this
        }

        fun build(): CustomConverterFactory {
            return CustomConverterFactory(mFactoryMap)
        }

    }
}