package db.poorya.namavatest.hilt

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("BaseURL")
    fun provideBaseURL(): String {
        return "https://api.vimeo.com/"
    }

    @Singleton
    @Provides
    fun provideGSon(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gson: Gson,
        @Named("BaseURL") baseURL: String
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().run {
                addHeader("Accept", "application/vnd.vimeo.video+json;version=3.4")
                addHeader(
                    "Authorization",
                    "Bearer 68d1cb674e9a52dd9d8c3dddee99e759"
                )// TODO: get from safe place!(encrypted)
                build()
            }
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}