package home.howework.data

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import home.howework.data.model.CurrencyInfoResponse
import home.howework.domain.CurrencyConverterRepoInteface.CurrencyConverterRepoInterface
import home.howework.domain.entity.CurrencyInfoDto
import home.howework.domain.entity.LastUpdateDto
import home.howework.domain.entity.PayLoadDto
import home.howework.domain.entity.RatesDto
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


class CurrencyConverterRepo @Inject constructor() : CurrencyConverterRepoInterface {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val Base_Url = "https://api.tinkoff.ru/v1/"
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val responseNull = CurrencyInfoDto(
            null, null, PayLoadDto(
                null,
                arrayListOf<RatesDto>()
            )
        )
    }


    interface TinkoffApi {
        @GET("currency_rates")
        suspend fun getCurrency(
            @Query("from") from: String,
            @Query("to") to: String
        ): Response<CurrencyInfoResponse>
    }

    object RetrofitInstance {
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Base_Url)
            .build()
        val tinkoffApi = retrofit.create(TinkoffApi::class.java)
    }

    override suspend fun getCurrency(from: String, to: String): CurrencyInfoDto {
        val responseNetwork = RetrofitInstance.tinkoffApi.getCurrency(from, to).body()
        delay(100)
        if (responseNetwork != null) {
            return responseNetwork.mapToCurrencyInfoResponse()
        }
        return responseNull
    }
}