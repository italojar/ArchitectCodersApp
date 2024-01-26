package es.architectcoders.spaceexplorer.framework.server

import es.architectcoders.spaceexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NasaInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .url(chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
                .build()
            )
            .build()
        return chain.proceed(request)
    }
}