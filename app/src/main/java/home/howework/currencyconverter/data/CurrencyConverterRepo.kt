package home.howework.currencyconverter.data

import android.annotation.SuppressLint
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import home.howework.currencyconverter.App
import home.howework.currencyconverter.entity.CurrencyInfoDto
import home.howework.currensyconverter.R
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class CurrencyConverterRepo {
    companion object {
        @SuppressLint("StaticFieldLeak")
        val context = App.context
        private val Base_Url = context?.getString(R.string.url)
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    suspend fun getCurrency(from:String,to:String): CurrencyInfoDto {
        val responseNetwork = RetrofitInstance.tinkoffApi.getCurrency(from,to).body()
        delay(100)
        if (responseNetwork != null) {
            Log.d("TTT2","Ответ: ${responseNetwork.payload.rates}")
        } else
        {
            Log.d("TTT@","Нет ответа")
        }
        return responseNetwork!!
    }

    interface TinkoffApi {
        @GET("currency_rates")
        suspend fun getCurrency(@Query("from") from: String,@Query("to") to: String): Response<CurrencyInfoDto>
    }
    object RetrofitInstance {
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Base_Url)
            .build()
        val tinkoffApi = retrofit.create(TinkoffApi::class.java)
    }
}