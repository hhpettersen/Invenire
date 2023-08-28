package no.app.invenire.datasource

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import no.app.invenire.domain.AdType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClientProvider {
     private val moshi = Moshi.Builder()
         .add(AdType::class.java, EnumJsonAdapter.create(AdType::class.java).withUnknownFallback(AdType.UNKNOWN))
         .addLast(KotlinJsonAdapterFactory())
         .build()

     private val httpClient = OkHttpClient.Builder()
         .addInterceptor(
             HttpLoggingInterceptor().apply {
                 level = HttpLoggingInterceptor.Level.BODY
             }
         )
         .build()

     private val retrofit = Retrofit.Builder()
         .baseUrl("https://gist.githubusercontent.com/baldermork/")
         .addConverterFactory(MoshiConverterFactory.create(moshi))
         .client(httpClient)
         .build()

     val client: RemoteDataSource = retrofit.create(RemoteDataSource::class.java)
}
